package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShiftRequest {

    private String shiftName;

    private String description;
}
