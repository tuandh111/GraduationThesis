package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShiftRequest {
    @NotNull
    private String shiftName;
    @NotNull
    private String description;
}
