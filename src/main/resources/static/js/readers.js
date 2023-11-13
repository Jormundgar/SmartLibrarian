const readersTBody = $("#readersTableBody");
const newReaderForm = $("#newReaderForm");
const editReaderForm = $("#form-edit");
const deleteReaderForm = $("#form-delete");

$(document).ready(async function () {
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
    console.log(reader.id);
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
            url: "http://localhost:8080/api/readers",
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