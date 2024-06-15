package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalHistoryDetailRequest {

    private  int medicalHistoryId;

    private int  patientId;

    private String description;

}
