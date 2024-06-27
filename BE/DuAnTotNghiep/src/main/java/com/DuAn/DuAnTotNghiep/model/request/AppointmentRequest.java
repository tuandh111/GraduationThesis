package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

import java.util.Date;
@Data
public class AppointmentRequest {
    private Date createAt;

    private  String note;

    private Date appointmentDate;

    private int appointmentType;

    private int appointmentStatus;

    private int appointmentPatientRecord;

    private int dentalStaffId;

    private int doctorId;

    private int patientId;
 }
