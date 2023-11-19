const booksTBody = $("#booksTableBody");
const searchBookForm = $("#form-search");
const releaseBookForm = $("#form-release");
const assignBookForm = $("#form-assign");
const newBookForm = $("#newBookForm");
const editBookForm = $("#form-edit");
const deleteBookForm = $("#form-delete");
const setPageNumber = $("#pageNumber");
const setSortType = $("#sortingType");
let byYear = false;
let page = 0;
let lines;

$(document).ready(async function () {
    await getLines();
    await getBooks();
});

async function getLines() {
    lines = await $.ajax({
        url: "http://localhost:8080/api/books/number",
        method: "GET",
        dataType: 'json'
    });
}

setPageNumber.on('click', async function(pages) {
    page = pages.target.id.split('-')[1];
    await getBooks();
});
setSortType.on('click', async function(sorting) {
    byYear = sorting.target.id.split('-')[1];
    await getBooks();
});

booksTBody.on('click', async function (event) {
    let id = event.target.id.split('-');
    await getId(id[1], id[0]);
});

async function getId(id, type) {
    let json = await $.ajax({
        url: `http://localhost:8080/api/books/item?id=${id}`,
        method: 'GET',
        dataType: 'json'
    });
    if (type === 'delete') {
        showDeleteModal(json);
    }
    if (type === 'edit') {
        showEditModal(json);
    }
    if (type === 'assign') {
        await showAssignModal(json);
    }
    if (type === 'release') {
        showReleaseModal(json);
    }
}

searchBookForm.on('submit', async function (event) {
    event.preventDefault();
    if (searchBookForm[0].checkValidity()) {
        const containValue = $("#searchFromBooks").val();
        window.location.href = `/books/search?contain=${containValue}`;
    }
})

// Assign book

assignBookForm.on('submit', async function (event){
    event.preventDefault();
    await assignBook();
    $("#assign-close").click();
});

const assignModal = new bootstrap.Modal(document.getElementById("assignModal"));

async function showAssignModal(book) {
    assignModal.show();
    const inputArr = [];
    Array.from(document.querySelector('#form-assign').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]));

    const readers = await $.ajax({
        url: 'http://localhost:8080/api/readers',
        method: 'GET',
        dataType: 'json'
    });

    inputArr.forEach(input => {
        const inputName = input.name;
        input.value = book[inputName];

        if (input.nodeName.toLowerCase() === 'select') {
            input.innerHTML = '';
            readers.forEach(reader => {
                const option = document.createElement('option');
                option.value = reader.name;
                option.text = reader.name;
                input.appendChild(option);
            });
            input.value = readers[0].name;
        }
    });
    const input = $("#form-assign > div:nth-child(1) > :nth-child(2)");
    input.val(book[input.attr('name')]);
    $("#assign-name").text("Assigning book " + book.name);
}

async function assignBook(){
    let id = $("#assign-id").val();
    let formData = {
        id: id,
        reader: $("#reader").val()
    };

    await $.ajax({
        url: `http://localhost:8080/api/books/assign`,
        method: 'PATCH',
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function () {
            getBooks();
        }
    });
}

// Release book

releaseBookForm.on('submit', async function (event) {
    event.preventDefault();
    await releaseBook();
    $("#release-close").click();
});

const releaseModal = new bootstrap.Modal(document.getElementById("releaseModal"));

function showReleaseModal(book) {
    releaseModal.show();
    const input = $("#form-release > div:nth-child(1) > :nth-child(2)");
    input.val(book[input.attr('name')]);
    $("#release-reader").text("Current reader:  " + book.reader);
    $("#release-name").text("Do you really want to release the book " + book.name + " ?");
}

async function releaseBook() {
    let id = $("#release-id").val();

    await $.ajax({
        url: `http://localhost:8080/api/books/release?id=${id}`,
        method: 'PATCH',
        success: function () {
            getBooks();
        }
    });
}

// Edit reader

editBookForm.on('submit', async function (event) {
    event.preventDefault();
    await editBook();
    $("#edit-close").click();
});

const editModal = new bootstrap.Modal(document.getElementById("editModal"));

function showEditModal(book) {
    editModal.show();
    const inputArr = [];
    Array.from(document.querySelector('#form-edit').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]));
    inputArr.forEach(input => input.value = book[input.name]);
}

async function editBook() {
    let id = document.querySelector('#form-edit')[0].value;
    let formData = {
        id: id,
        name: $("#edit-title").val(),
        author: $("#edit-author").val(),
        yearOfPublish: $("#edit-yearOfPublish").val()
    };

    await $.ajax({
        url: "http://localhost:8080/api/books",
        type: "PATCH",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function () {
            getBooks();
        }
    });
}

// Delete reader

deleteBookForm.on('submit', async function (event) {
    event.preventDefault();
    await deleteBook();
    $("#delete-close").click();
});

const deleteModal = new bootstrap.Modal(document.getElementById("deleteModal"));

function showDeleteModal(book) {
    deleteModal.show();
    const input = $("#form-delete > div:nth-child(1) > :nth-child(2)");
    input.val(book[input.attr('name')]);
    $("#delete-name").text("Do you really want to delete the book " + book.name + " ?");
}

async function deleteBook() {
    let id = $("#delete-id").val();

    await $.ajax({
        url: `http://localhost:8080/api/books?id=${id}`,
        method: 'DELETE',
        success: function () {
            getBooks();
        }
    });
}

// New reader

newBookForm.on('submit', async function (event) {
    event.preventDefault();
    await newBook();
});

async function newBook() {
    let formData = {
        name: $("#name").val(),
        author: $("#author").val(),
        yearOfPublish: $("#yearOfPublish").val()
    };

    $.ajax({
        url: "http://localhost:8080/api/books",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function () {
            getBooks();
            $("#name").val('');
            $("#author").val('');
            $("#yearOfPublish").val('');
            let createBookForm = $("#newBookForm");
            createBookForm[0].reset();
            createBookForm.removeClass('was-validated');
            $('#nav-home-tab').tab('show');
        }
    });
}

async function getBooks() {

    let bookJson = await $.ajax({
        url: `http://localhost:8080/api/books?byYear=${byYear}&pageNumber=${page}`,
        method: "GET",
        dataType: 'json'
    });
    booksTBody.empty();
    bookJson.forEach((book, index) => {
        const row = $("<tr>").html(`
                <td>${index + 1}</td>
                <td>${book.name}</td>
                <td class="text-center">${book.author}</td>
                <td class="text-center align-middle">${book.yearOfPublish}</td>
                <td class="text-center">
                    ${book.reader === null ? 
            `<button class="btn btn-outline-secondary btn-sm"
                                id="assign-${book.id}"
                                data-bs-toggle="modal"
                                data-bs-target="#assign-modal">Assign</button>` :
            `<button class="btn btn-outline-danger btn-sm"
                                id="release-${book.id}"
                                data-bs-toggle="modal"
                                data-bs-target="#release-modal">Release</button>`}
                </td>
                <td class="text-center"> 
                    <button class="btn btn-outline-primary btn-sm"
                            id="edit-${book.id}"
                            data-bs-toggle="modal"
                            data-bs-target="#edit-modal">Edit</button>
                </td>
                <td class="text-center">
                    <button class="btn btn-outline-danger btn-sm"
                            id="delete-${book.id}"
                            data-bs-toggle="modal"
                            data-bs-target="#delete-modal">Delete</button>
                </td>
            `);
        booksTBody.append(row);
    });
    await getLines();
    updatePaginationButtons();
}
