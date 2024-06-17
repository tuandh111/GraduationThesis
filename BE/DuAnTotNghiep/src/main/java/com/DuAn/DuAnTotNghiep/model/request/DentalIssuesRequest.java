package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DentalIssuesRequest {

    private String name;

    private String description;
}
