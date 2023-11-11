const readersTBody = document.querySelector('#readersTableBody')
const newReaderForm = document.querySelector('#newReaderForm')
const readerTakenBooks = document.querySelector('#form-bookList')
const editReaderForm = document.querySelector('#form-edit')
const deleteReaderForm = document.querySelector('#form-delete')

window.addEventListener('load', async function (){
    await getReaders();
})

readersTBody.addEventListener('click', async function (element){
    let id = element.target.id.split('-')
    await getId(id[1], id[0])
})
newReaderForm.addEventListener('submit', async function (element){
    element.preventDefault()
    await newReader();
})
readerTakenBooks.addEventListener('submit', async function (element){
    element.preventDefault()
    await showBookList()
    document.querySelector("#bookList-close").click()
})
editReaderForm.addEventListener('submit', async function (element){
    element.preventDefault()
    await editReader()
    document.querySelector("#edit-close").click()
})
deleteReaderForm.addEventListener('submit', async function (element){
    element.preventDefault()
    await deleteReader()
    document.querySelector("#delete-close").click()
})

async function getReaders() {
    let responseFromReadersPage = await fetch("http://localhost:8080/api/readers", {
        method: "GET",
    });
    let readerJson = await responseFromReadersPage.json();
    const tbody = document.getElementById("readersTableBody");
    readerJson.forEach(reader => {
        const row = document.createElement("tr");
        row.innerHTML = `
                <td>${reader.id}</td>
                <td>${reader.name}</td>
                <td class="text-center align-middle">${reader.yearOfBirth}</td>
                <td class="text-center">
                    <button class="btn btn-outline-secondary btn-sm"
                    id="bookList-${reader.id}"
                    data-bs-toggle="modal"
                    data-bs-target="#bookList-modal">Booklist</button>
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
            `;
        tbody.appendChild(row);
    })
}

async function getId(id, type) {
    let response = await fetch(`http://localhost:8080/api/readers/item?id=${id}`);
    let json = await response.json();
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

const deleteModal = new bootstrap.Modal(document.getElementById("deleteModal"))

function showDeleteModal(user) {
    deleteModal.show();
    const inputArr = []
    Array.from(document.querySelector('#form-delete').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]))
    inputArr.forEach(input => input.value = user[input.name])
}

async function deleteReader() {
    let id = $("#delete-id").val();
    let response = await fetch(`http://localhost:8080/api/readers?id=${id}`, {
        method: 'DELETE'
    });
    if (response.ok) {
        window.location.href = "http://localhost:8080/readers";
    }
}

async function newReader() {
    let formData = {
        name: $("#name").val(),
        yearOfBirth: $("#yearOfBirth").val()

    };
    let response = await fetch("http://localhost:8080/api/readers", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    });
    if (response.ok) {
        window.location.href = "http://localhost:8080/readers";
    }
}

const bookListModal = new bootstrap.Modal(document.getElementById("bookListModal"))

function showBookListModal(reader) {
    bookListModal.show();
    const inputArr = []
    Array.from(document.querySelector('#form-bookList').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]))
    inputArr.forEach(input => input.value = reader[input.name])
}

async function showBookList() {
    let id = document.querySelector('#form-bookList')[0].value;
    let response = await fetch(`http://localhost:8080/api/readers?id=${id}`, {
        method: 'DELETE'
    });
    if (response.ok) {
        window.location.href = "http://localhost:8080/readers";
    }
}

const editModal = new bootstrap.Modal(document.getElementById("editModal"))

function showEditModal(reader) {
    editModal.show();
    const inputArr = []
    Array.from(document.querySelector('#form-edit').children)
        .forEach(div => inputArr.push(Array.from(div.children)[1]))
    inputArr.forEach(input => input.value = reader[input.name])
}

async function editReader() {
    let id = document.querySelector('#form-edit')[0].value;
    let formData = {
        id: id,
        name: $("#edit-name").val(),
        yearOfBirth: $("#edit-yearOfBirth").val()
    };
    let response = await fetch(`http://localhost:8080/api/readers`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    });
    if (response.ok) {
        window.location.href = "http://localhost:8080/readers";
    }

}