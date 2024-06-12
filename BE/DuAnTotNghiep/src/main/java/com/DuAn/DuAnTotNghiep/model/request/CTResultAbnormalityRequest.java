package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CTResultAbnormalityRequest {
    @NotNull
    private String description;

    private int abnormalityId;

    private int appointmentCTResult;

}
