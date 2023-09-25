package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "Book REST")
@Tag(name = "Book REST", description = "Book API description")
@RequestMapping("/api/book")
public interface BookRestApi {

    @GetMapping
    @ApiOperation(value = "Get all Books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Books found"),
            @ApiResponse(code = 204, message = "Books not present")}
    )
    ResponseEntity<List<BookDTO>> getAll();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book found"),
            @ApiResponse(code = 404, message = "Book not found")}
    )
    ResponseEntity<BookDTO> get(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id);

    @PostMapping
    @ApiOperation(value = "Create Book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book created"),
            @ApiResponse(code = 400, message = "Book not created")}
    )
    ResponseEntity<BookDTO> create(@ApiParam(name = "book", value = "BookDto") @Valid @RequestBody BookDTO bookDTO);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book updated"),
            @ApiResponse(code = 400, message = "Book not updated")}
    )
    ResponseEntity<BookDTO> update(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id,
                                   @ApiParam(name = "book", value = "BookDto") @Valid @RequestBody BookDTO bookDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book deleted"),
            @ApiResponse(code = 404, message = "Book not found")}
    )
    ResponseEntity<Void> delete(@ApiParam(name = "id", value = "Book.id") @PathVariable(value = "id") Integer id);
}
