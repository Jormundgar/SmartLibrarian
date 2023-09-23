package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
