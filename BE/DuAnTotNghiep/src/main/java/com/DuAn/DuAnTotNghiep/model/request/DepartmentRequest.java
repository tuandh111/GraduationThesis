package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequest {

    private String departmentName;

    private String description;
}
