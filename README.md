
# SmartLibrarian

>Using: `Java 17`,`Spring Boot`,`REST Api`, `Swagger`,  `Spring Data JPA`, `MapStruct`,`PostgreSQL`,`Docker`,`Lombok`,
> `Bootstrap 5`,`HTML`,`JavaScript`, `jQuery`

>TODO: 
> - `Spring Security`
> - `Spring Cloud`

## Build Project
      git clone --branch 1.1.1 git@github.com:Jormundgar/SmartLibrarian.git
      cd SmartLibrarian 
      docker-compose up
#### Entry point:

      http://localhost:8080/books

## Project Description

This project focuses on developing a web application for digital book management, specifically designed for libraries. The application provides library staff with the ability to register new readers, issue books to them, and release books when they are returned to the library.

## Project Functionality

The web application encompasses the following features:

1. **Reader Management:**
   - Add, modify, and delete reader profiles.
   - Field validation for reader information during creation and editing.

2. **Book Management:**
   - Add, modify, and delete book entries.
   - Field validation for book details during creation and editing.

3. **Listing and Pagination:**
   - Display a comprehensive list of all readers in the library.
   - Display a complete list of all books in the library.
   - Support for both full listing and paginated view (5 records per page).
   - Clickable links to navigate to individual reader pages for more information.
   - Clickable links to navigate to individual book pages for more information.

4. **Sorting Options:**
   - Enable sorting of readers and books based on specific criteria.
   - Sorting options include sorting by ID or by year.
   - For readers, the sorting is based on birth year, while for books, it is based on publication year.

5. **Individual Reader Pages:**
   - Show detailed information about a reader.
   - Display a list of books borrowed by the reader.
   - If the reader has not borrowed any books, display the message "This reader has not borrowed any books yet."

6. **Individual Book Pages:**
   - Provide detailed information about a book.
   - Show the name of the reader who has borrowed the book.
   - If the book is not borrowed by anyone, display the message "This book is available."

7. **Book Assignment and Release:**
   - On the book page, if the book is available, display a dropdown list with all readers and an "Assign Book" button.
   - Library staff can click the button to assign the book to a reader, adding it to their book list.
   - On the book page, if the book is currently borrowed, display a "Release Book" button next to the borrower's name.
   - Library staff can click the button to mark the book as returned, making it available again and removing it from the borrower's book list.

8. **Search Functionality:**
   - Added functionality for searching within the book list.
   - Search results indicate whether the book is available or not.
   - If the book is not available, it displays the name of the reader who has borrowed it.

9. **Book Return Control:**
   - When assigning a book to a reader, the database records the borrowing time.
   - If the book is not returned after 10 days, it is displayed in red in the list of borrowed books on the reader's page.

This web application simplifies book management for libraries, streamlining the process of registering readers, issuing books, and managing book returns. It aims to enhance efficiency and provide a user-friendly experience for library staff.
