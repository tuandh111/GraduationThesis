package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentPatientRecordRequest {
    @NotNull
    private int patientId;
    private Date createAt;
    private String currentCondition;
    @NotNull
    private String reExamination;
}
