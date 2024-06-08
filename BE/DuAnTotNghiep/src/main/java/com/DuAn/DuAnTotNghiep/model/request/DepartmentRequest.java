package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotNull
    private String departmentName;
    @NotNull
    private String description;
}
