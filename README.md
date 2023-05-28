
# SmartLibrarian
>Using: `Spring MVC`,`Spring Data JPA`, `Hibernate`,`Servlet API`,`Hibernate Validator`,`PostgreSQL`,`Lombok`,`Thymeleaf`,`HTML`, `CSS`, `JavaScript` 

### Commits
- `v.1.0.RELEASE` 
- `v.1.1` Rebuild project from JDBC Template to Hibernate and Data JPA
- `v.1.2` Added the option to display lists of library users and books both in full and paginated (5 records per page). Also added sorting by ID or by year (birth year for users and publication year for books).
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

3. **Reader Listing:**
    - Display a comprehensive list of all readers in the library.
    - Support for both full listing and paginated view (5 records per page).
    - Clickable links to navigate to individual reader pages for more information.

4. **Book Listing:**
    - Display a complete list of all books in the library.
    - Support for both full listing and paginated view (5 records per page).
    - Clickable links to navigate to individual book pages for more information.

5. **Sorting Options:**
   - Enable sorting of readers and books based on specific criteria.
   - Sorting options include sorting by ID or by year.
   - For readers, the sorting is based on birth year, while for books, it is based on publication year.

6. **Individual Reader Pages:**
    - Show detailed information about a reader.
    - Display a list of books borrowed by the reader.
    - If the reader has not borrowed any books, display the message "This person has not borrowed any books yet."

7. **Individual Book Pages:**
    - Provide detailed information about a book.
    - Show the name of the reader who has borrowed the book.
    - If the book is not borrowed by anyone, display the message "This book is available."

8. **Book Release:**
    - On the book page, if the book is currently borrowed, display a "Release Book" button next to the borrower's name.
    - Library staff can click the button to mark the book as returned, making it available again and removing it from the borrower's book list.

9. **Book Assignment:**
    - On the book page, if the book is available, display a dropdown list with all readers and an "Assign Book" button.
    - Library staff can click the button to assign the book to a reader, adding it to their book list.

This web application simplifies book management for libraries, streamlining the process of registering readers, issuing books, and managing book returns. It aims to enhance efficiency and provide a user-friendly experience for library staff and readers.
