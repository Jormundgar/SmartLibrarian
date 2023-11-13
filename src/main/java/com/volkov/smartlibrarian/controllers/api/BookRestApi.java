package com.volkov.smartlibrarian.controllers.api;

import com.volkov.smartlibrarian.dto.BookDTO;
import com.volkov.smartlibrarian.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/books")
@Tag(name = "Books REST Controller", description = "API for managing books")
public interface BookRestApi {

    @Operation(
            summary = "Get all books",
            description = "Retrieve a list of all books",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No books found"
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<BookDTO>> getAll(
            @RequestParam(defaultValue = "false", required = false) Boolean byYear,
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber
    );

    @Operation(
            summary = "Get a book by ID",
            description = "Retrieve a book by its unique ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @GetMapping("/item")
    ResponseEntity<BookDTO> getById(@RequestParam Integer id);

    @Operation(
            summary = "Get a book List by reader ID",
            description = "Retrieve a book`s list by its reader ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Books retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @GetMapping("/reader")
    ResponseEntity<List<BookDTO>> getByReaderId(@RequestParam Integer id);

    @Operation(
            summary = "Create a new book",
            description = "Create a new book record",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Book created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    )
            }
    )
    @PostMapping
    ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO);

    @Operation(
            summary = "Update a book",
            description = "Update an existing book record",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @PatchMapping
    ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO);

    @Operation(
            summary = "Delete a book",
            description = "Delete an existing book record",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Book deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> delete(@RequestParam Integer id);

    @Operation(
            summary = "Books search",
            description = "Find books based on a user-entered query. Returns matching books",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No books found"
                    )
            }
    )
    @PostMapping("/search")
    ResponseEntity<List<BookDTO>> search(@RequestParam String contain);

    @Operation(
            summary = "Assign a book to a reader",
            description = "Associate a book with a reader in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @PatchMapping("/assign")
    ResponseEntity<Void> assign(@RequestBody BookDTO bookDTO);

    @Operation(
            summary = "Release a book from a reader",
            description = "Remove the association between a book and its current reader in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found"
                    )
            }
    )
    @PatchMapping("/release")
    ResponseEntity<Void> release(@RequestParam Integer id);
}
