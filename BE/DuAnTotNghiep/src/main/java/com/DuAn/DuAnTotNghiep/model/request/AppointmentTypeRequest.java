package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentTypeRequest {
    @NotNull
    private String type;
    @NotNull
    private String description;
}
