const searchTable = $("#searchResultTable");
const searchBookForm = $("#form-search");
const tableBlock = $("#table-block");
const noResult = $(".no-result");
let contain;

$(document).ready(async function () {
    const urlParams = new URLSearchParams(window.location.search);
    contain = urlParams.get('contain');
    await getResults();
});

searchBookForm.on('submit', async function (event) {
    event.preventDefault();
    if (searchBookForm[0].checkValidity()) {
        contain = $("#searchFromSearch").val();
        getResults().then();
    }
})

async function getResults() {
    try {
        let searchJson = await $.ajax({
            url: `http://localhost:8080/api/books/search?contain=${contain}`,
            method: "GET",
            dataType: 'json'
        });
        searchTable.empty()
        noResult.hide();
        tableBlock.show();
        searchJson.forEach((book) => {
            const row = $("<tr>").html(`
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.yearOfPublish}</td>
                <td>
                    ${book.reader === null ?
                'The book is free' :
                `${book.reader}`}
                </td>
            `);
            searchTable.append(row);
        });
    } catch (e) {
        tableBlock.hide();
        noResult.show();
        noResult.append($("<p class='mt-2'>There are not any book</p>"));
    }
}
