package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.BookDTO;
import com.volkov.smartlibrarian_boot.dto.PersonDTO;
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

@Api(tags = "People REST")
@Tag(name = "People REST", description = "People API description")
@RequestMapping("/api/people")
public interface PeopleRestApi {

    @GetMapping
    @ApiOperation(value = "Get all People")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "People found"),
            @ApiResponse(code = 204, message = "People not present")}
    )
    ResponseEntity<List<PersonDTO>> getAll();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Person by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person found"),
            @ApiResponse(code = 404, message = "Person not found")}
    )
    ResponseEntity<PersonDTO> get(@ApiParam(name = "id", value = "Person.id")
                                  @PathVariable(value = "id") Integer id);

    @PostMapping
    @ApiOperation(value = "Create Person")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person created"),
            @ApiResponse(code = 400, message = "Person not created")}
    )
    ResponseEntity<PersonDTO> create(@ApiParam(name = "person", value = "PersonDto")
                                     @Valid @RequestBody PersonDTO personDTO);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update Person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person updated"),
            @ApiResponse(code = 400, message = "Person not updated")}
    )
    ResponseEntity<PersonDTO> update(@ApiParam(name = "id", value = "Person.id")
                                     @PathVariable(value = "id") Integer id,
                                     @ApiParam(name = "person", value = "PersonDto")
                                     @Valid @RequestBody PersonDTO personDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Person by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person deleted"),
            @ApiResponse(code = 404, message = "Person not found")}
    )
    ResponseEntity<Void> delete(@ApiParam(name = "id", value = "Person.id") @PathVariable(value = "id") Integer id);
}
