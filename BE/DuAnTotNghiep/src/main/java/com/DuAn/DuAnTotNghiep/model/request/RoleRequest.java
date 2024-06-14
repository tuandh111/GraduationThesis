package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequest {
    @NotNull
    private String roleName;
    @NotNull
    private String description;
}
