package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TreatmentRequest {

    private String name;

    private String description;
}
