package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentTypeRequest {

    private String type;

    private String description;
}
