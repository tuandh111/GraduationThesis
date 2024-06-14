package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

@Data
public class PrescriptionRequest {
    private String description;
    private Integer appointmentPatientRecordId;
    private Integer treatmentDurationId;
}
