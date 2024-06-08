package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TreatmentRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
