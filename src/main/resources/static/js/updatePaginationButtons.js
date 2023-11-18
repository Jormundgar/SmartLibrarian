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