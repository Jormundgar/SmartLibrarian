
# SmartLibrarian
>Using: `Spring MVC`,`Spring Data JPA`, `Hibernate`,`Servlet API`,`Hibernate Validator`,`PostgreSQL`,`Lombok`,`Thymeleaf`,`HTML`, `CSS` 

### Commits
- `v.1.0.RELEASE` 
- `v.1.1` Rebuild project from JDBC Template to Hibernate and Data JPA
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
    - Clickable links to navigate to individual reader pages for more information.

4. **Book Listing:**
    - Display a complete list of all books in the library.
    - Clickable links to navigate to individual book pages for more information.

5. **Individual Reader Pages:**
    - Show detailed information about a reader.
    - Display a list of books borrowed by the reader.
    - If the reader has not borrowed any books, display the message "This person has not borrowed any books yet."

6. **Individual Book Pages:**
    - Provide detailed information about a book.
    - Show the name of the reader who has borrowed the book.
    - If the book is not borrowed by anyone, display the message "This book is available."

7. **Book Release:**
    - On the book page, if the book is currently borrowed, display a "Release Book" button next to the borrower's name.
    - Library staff can click the button to mark the book as returned, making it available again and removing it from the borrower's book list.

8. **Book Assignment:**
    - On the book page, if the book is available, display a dropdown list with all readers and an "Assign Book" button.
    - Library staff can click the button to assign the book to a reader, adding it to their book list.

This web application simplifies book management for libraries, streamlining the process of registering readers, issuing books, and managing book returns. It aims to enhance efficiency and provide a user-friendly experience for library staff and readers.
