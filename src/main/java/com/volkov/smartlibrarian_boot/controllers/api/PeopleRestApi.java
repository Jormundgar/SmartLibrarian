package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/people")
@Tag(name = "Users REST", description = "API for managing readers")
public interface PeopleRestApi {

    @Operation(
            summary = "Get all Readers",
            description = "Retrieve a list of all readers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of readers retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No readers found"
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<PersonDTO>> getAll();

//    @GetMapping("/{id}")
//    @ApiOperation(value = "Get Person by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Person found"),
//            @ApiResponse(code = 404, message = "Person not found")}
//    )
//    ResponseEntity<PersonDTO> get(@ApiParam(name = "id", value = "Person.id")
//                                  @PathVariable(value = "id") Integer id);
//
//    @PostMapping
//    @ApiOperation(value = "Create Person")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Person created"),
//            @ApiResponse(code = 400, message = "Person not created")}
//    )
//    ResponseEntity<PersonDTO> create(@ApiParam(name = "person", value = "PersonDto")
//                                     @Valid @RequestBody PersonDTO personDTO);
//
//    @PatchMapping("/{id}")
//    @ApiOperation(value = "Update Person")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Person updated"),
//            @ApiResponse(code = 400, message = "Person not updated")}
//    )
//    ResponseEntity<PersonDTO> update(@ApiParam(name = "id", value = "Person.id")
//                                     @PathVariable(value = "id") Integer id,
//                                     @ApiParam(name = "person", value = "PersonDto")
//                                     @Valid @RequestBody PersonDTO personDTO);
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete Person by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Person deleted"),
//            @ApiResponse(code = 404, message = "Person not found")}
//    )
//    ResponseEntity<Void> delete(@ApiParam(name = "id", value = "Person.id") @PathVariable(value = "id") Integer id);
}
