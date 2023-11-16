const readersTBody = $("#readersTableBody");
const newReaderForm = $("#newReaderForm");
const editReaderForm = $("#form-edit");
const deleteReaderForm = $("#form-delete");
const setPageNumber = $("#pageNumber");
const setSortType = $("#sortingType");
let byYear = false;
let page = 0;
let lines;

$(document).ready(async function () {
    await getLines();
    await getReaders();
});

async function getLines() {
    lines = await $.ajax({
        url: "http://localhost:8080/api/readers/number",
        method: "GET",
        dataType: 'json'
    });
}

function updatePaginationButtons() {
    let paginationContainer = $(".pagination-container");
    paginationContainer.empty();
    let paginationGroup = $("<div class='btn-group' role='group' aria-label='Pagination group'></div>");
    let chooseButton = $("<button type='button' class='btn btn-outline-dark disabled'>Choose the page:</button>");
    paginationGroup.append(chooseButton);

    for (let i = 0; i < Math.ceil(lines.numberOfRecords / 5); i++) {
        const pageButton = $(`<button class="btn btn-outline-primary" name="pageButton" id="page-${i + 1}">${i + 1}</button>`);
        paginationGroup.append(pageButton);
    }
    let allButton = $("<button class='btn btn-outline-primary' id='page-0'>Show all</button>");
    paginationGroup.append(allButton);
    paginationContainer.append(paginationGroup);
}

setPageNumber.on('click', async function(pages) {
    page = pages.target.id.split('-')[1];
    await getReaders();
});
setSortType.on('click', async function(sorting) {
    byYear = sorting.target.id.split('-')[1];
    await getReaders();
});

readersTBody.on('click', async function (event) {
    let id = event.target.id.split('-');
    await getId(id[1], id[0]);
});

async function getId(id, type) {
    let json = await $.ajax({
        url: `http://localhost:8080/api/readers/item?id=${id}`,
        method: 'GET',
        dataType: 'json'
    });
    if (type === 'delete') {
        showDeleteModal(json);
    }
    if (type === 'edit') {
        showEditModal(json);
    }
    if (type === 'bookList') {
        showBookListModal(json);
    }
}

// Book list

const bookListModal = new bootstrap.Modal(document.getElementById("bookListModal"));

function showBookListModal(reader) {
    bookListModal.show();
    getBooks(reader.id).then();
}

// Edit reader

editReaderForm.on('submit', async function (event) {
    event.preventDefault();
    await editReader();
    $("#edit-close").click();
});

const editModal = new bootstrap.Modal(document.getElementById("editModal"));

function showEditModal(reader) {
    editModal.show();
    const inputArr = [];
    Array.from(document.querySelector('#form-edit').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]));
    inputArr.forEach(input => input.value = reader[input.name]);
}

async function editReader() {
    let id = document.querySelector('#form-edit')[0].value;
    let formData = {
        id: id,
        name: $("#edit-name").val(),
        yearOfBirth: $("#edit-yearOfBirth").val()
    };

    await $.ajax({
        url: "http://localhost:8080/api/readers",
        type: "PATCH",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function () {
            getReaders();
        }
    });
}

// Delete reader

deleteReaderForm.on('submit', async function (event) {
    event.preventDefault();
    await deleteReader();
    $("#delete-close").click();
});

const deleteModal = new bootstrap.Modal(document.getElementById("deleteModal"));

function showDeleteModal(user) {
    deleteModal.show();
    const input = $("#form-delete > div:nth-child(1) > :nth-child(2)");
    input.val(user[input.attr('name')]);
    $("#delete-name").text("Do you really want to delete the reader " + user.name + " ?");
}

async function deleteReader() {
    let id = $("#delete-id").val();

        await $.ajax({
            url: `http://localhost:8080/api/readers?id=${id}`,
            method: 'DELETE',
            success: function () {
                getReaders();
            }
        });
}

// New reader

newReaderForm.on('submit', async function (event) {
    event.preventDefault();
    await newReader();
});

async function newReader() {
    let formData = {
        name: $("#name").val(),
        yearOfBirth: $("#yearOfBirth").val()
    };

    $.ajax({
        url: "http://localhost:8080/api/readers",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function () {
            getReaders();
            $("#name").val('');
            $("#yearOfBirth").val('');
            let createReaderForm = $("#newReaderForm");
            createReaderForm[0].reset();
            createReaderForm.removeClass('was-validated');
            $('#nav-home-tab').tab('show');
        }
    });
}

async function getReaders() {

        let readerJson = await $.ajax({
            url: `http://localhost:8080/api/readers?byYear=${byYear}&pageNumber=${page}`,
            method: "GET",
            dataType: 'json'
        });
        readersTBody.empty();
        readerJson.forEach((reader, index) => {
            const row = $("<tr>").html(`
                <td>${index + 1}</td>
                <td>${reader.name}</td>
                <td class="text-center align-middle">${reader.yearOfBirth}</td>
                <td class="text-center">
                    ${reader.readerBooks.length > 0 ?
                `<button class="btn btn-outline-secondary btn-sm"
                                id="bookList-${reader.id}"
                                data-bs-toggle="modal"
                                data-bs-target="#bookList-modal">Booklist</button>` :
                'No books taken'}
                </td>
                <td class="text-center"> 
                    <button class="btn btn-outline-primary btn-sm"
                            id="edit-${reader.id}"
                            data-bs-toggle="modal"
                            data-bs-target="#edit-modal">Edit</button>
                </td>
                <td class="text-center">
                    <button class="btn btn-outline-danger btn-sm"
                            id="delete-${reader.id}"
                            data-bs-toggle="modal"
                            data-bs-target="#delete-modal">Delete</button>
                </td>
            `);
            readersTBody.append(row);
        });
    await getLines();
    updatePaginationButtons();
}

async function getBooks(id) {
    let books = await $.ajax({
        url: `http://localhost:8080/api/books/reader?id=${id}`,
        method: 'GET',
        dataType: 'json'
    });

    const tbody = $("#readerBooksTableBody");
    tbody.empty();
    books.forEach(book => {
        const row = $("<tr>").html(`
                <td style="${book.expired ? 'color: red;' : ''}">${book.name}</td>
                <td style="${book.expired ? 'color: red;' : ''}">${book.author}</td>
            `);
        tbody.append(row);
    });
}
