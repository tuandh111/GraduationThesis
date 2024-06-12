package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DentalSuppliesRequest {
    @NotNull
    private String suppliesName;
    @NotNull
    private String description;
    @NotNull
    private int DistributionSuppliesId;
}
