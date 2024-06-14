package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceTreatmentRequest {
    @NotNull
    private int serviceId;

    private int treatment;
    @NotNull
    private String description;
}
