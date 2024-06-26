package com.llakhmann.backend.controllers.api;

import com.llakhmann.backend.dto.ErrorDTO;
import com.llakhmann.backend.dto.RecordsNumberDTO;
import com.llakhmann.backend.dto.ReaderDTO;
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

@RequestMapping("/api/readers")
@Tag(name = "Readers REST Controller", description = "API for managing readers")
public interface ReaderRestApi {

    @Operation(
            summary = "Get all readers",
            description = "Retrieve a list of all readers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of readers retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReaderDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No readers found"
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<ReaderDTO>> getAll(
            @RequestParam(defaultValue = "false", required = false) Boolean byYear,
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber
    );

    @Operation(
            summary = "Get a reader by ID",
            description = "Retrieve a reader by its unique ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reader retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReaderDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reader not found"
                    )
            }
    )
    @GetMapping("/item")
    ResponseEntity<ReaderDTO> getById(@RequestParam Integer id);

    @Operation(
            summary = "Get number of readers",
            description = "Get number of records of readers in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Number of records retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RecordsNumberDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No records found"
                    )
            }
    )
    @GetMapping("/number")
    ResponseEntity<RecordsNumberDTO> getNumberOfRecords();

    @Operation(
            summary = "Create a new reader",
            description = "Create a new reader record",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Reader created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReaderDTO.class)
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
    ResponseEntity<ReaderDTO> create(@RequestBody ReaderDTO readerDTO);

    @Operation(
            summary = "Update a reader",
            description = "Update an existing reader record",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reader updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReaderDTO.class)
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
                            description = "Reader not found"
                    )
            }
    )
    @PatchMapping
    ResponseEntity<ReaderDTO> update(@RequestBody ReaderDTO readerDTO);

    @Operation(
            summary = "Delete a reader",
            description = "Delete an existing reader record",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Reader deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reader not found"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> delete(@RequestParam Integer id);
}
