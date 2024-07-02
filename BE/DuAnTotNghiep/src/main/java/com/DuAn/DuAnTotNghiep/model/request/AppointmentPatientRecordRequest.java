package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentPatientRecordRequest {

    private int patientId;
    private Date createAt;
    private String currentCondition;

    private String reExamination;

    private boolean isDeleted;
}
