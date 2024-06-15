package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DentalSuppliesRequest {

    private String suppliesName;

    private String description;

    private int DistributionSuppliesId;
}
