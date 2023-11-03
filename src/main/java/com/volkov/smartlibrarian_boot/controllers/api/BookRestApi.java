package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/books")
@Tag(name = "Books REST", description = "API for managing books")
public interface BookRestApi {

    @Operation(
            summary = "Get all Books",
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
    ResponseEntity<List<BookDTO>> getAll();

//    @GetMapping("/{id}")
//    @ApiOperation(value = "Get Book by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Book found"),
//            @ApiResponse(code = 404, message = "Book not found")}
//    )
//    ResponseEntity<BookDTO> get(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id);
//
//    @PostMapping
//    @ApiOperation(value = "Create Book")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Book created"),
//            @ApiResponse(code = 400, message = "Book not created")}
//    )
//    ResponseEntity<BookDTO> create(@ApiParam(name = "book", value = "BookDto") @Valid @RequestBody BookDTO bookDTO);
//
//    @PatchMapping("/{id}")
//    @ApiOperation(value = "Update Book")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Book updated"),
//            @ApiResponse(code = 400, message = "Book not updated")}
//    )
//    ResponseEntity<BookDTO> update(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id,
//                                   @ApiParam(name = "book", value = "BookDto") @Valid @RequestBody BookDTO bookDTO);
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete Book by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Book deleted"),
//            @ApiResponse(code = 404, message = "Book not found")}
//    )
//    ResponseEntity<Void> delete(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id);
}
