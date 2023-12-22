package com.volkov.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
@Setter
@Schema(description = "Role entity")
public class RoleDTO {

    @ReadOnlyProperty
    @Schema(description = "Role UID")
    private Integer id;
    @Schema(description = "Role`s title")
    private String name;
}
