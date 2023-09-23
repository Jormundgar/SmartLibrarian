package com.volkov.smartlibrarian_boot.controllers.api;

import com.volkov.smartlibrarian_boot.dto.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
