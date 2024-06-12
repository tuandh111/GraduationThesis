package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalHistoryDetailRequest {
    @NotNull
    private  int medicalHistoryId;
    @NotNull
    private int  patientId;
    @NotNull
    private String description;

}
