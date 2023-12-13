package com.volkov.security.controllers.api;

import com.volkov.security.dto.ErrorDTO;
import com.volkov.security.dto.RoleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/api/roles")
@Tag(name = "Roles REST Controller", description = "API for managing roles")
public interface RoleRestApi {

    @Operation(
            summary = "Create a new role",
            description = "Create a new role record",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Role created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleDTO.class)
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
    ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO);

    @Operation(
            summary = "Update a role",
            description = "Update an existing role record",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Role updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RoleDTO.class)
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
                            description = "Role not found"
                    )
            }
    )
    @PatchMapping
    ResponseEntity<RoleDTO> update(@RequestBody RoleDTO roleDTO);

    @Operation(
            summary = "Delete a role",
            description = "Delete an existing role record",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Role deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Role not found"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> delete(@RequestParam Integer id);
}
