package com.volkov.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
@Setter
@Schema(description = "The number of records in the database")
public class RecordsNumberDTO {

    @ReadOnlyProperty
    @Schema(description = "The number of records")
    private Long numberOfRecords;

    public RecordsNumberDTO(Long numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
}
