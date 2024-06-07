package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalHistoryRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;

}
