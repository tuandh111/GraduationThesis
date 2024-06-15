package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequest {
    private String roleName;

    private String description;
}
