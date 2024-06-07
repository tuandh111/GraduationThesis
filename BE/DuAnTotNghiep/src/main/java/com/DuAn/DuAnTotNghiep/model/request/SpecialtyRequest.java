package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecialtyRequest {
    @NotNull

    private String specialtyName;

    @NotNull
    private String description;
}
