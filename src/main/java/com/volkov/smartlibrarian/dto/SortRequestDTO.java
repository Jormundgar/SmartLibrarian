package com.volkov.smartlibrarian.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortRequestDTO {

    private String sortDirection;
    private boolean byYear;
}
