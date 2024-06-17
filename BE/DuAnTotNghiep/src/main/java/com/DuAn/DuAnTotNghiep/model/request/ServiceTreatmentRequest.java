package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceTreatmentRequest {

    private int serviceId;

    private int treatment;

    private String description;
}
