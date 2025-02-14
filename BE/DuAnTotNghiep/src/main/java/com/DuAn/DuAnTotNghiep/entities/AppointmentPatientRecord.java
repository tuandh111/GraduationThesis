package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AppointmentPatientRecord {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer appointmentPatientRecordId;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String currentCodition;

    private String reExamination;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "appointmentTreatmentId")
    @JsonIgnore
    private List<AppointmentTreatment> appointmentTreatments;

    @OneToMany(mappedBy = "appointmentRecordIssuesId")
    @JsonIgnore
    private List<AppointmentRecordIssues> appointmentRecordIssues;


    @OneToOne(mappedBy = "appointmentPatientRecord")
    @JsonIgnore
    private Appointment appointments;


    private int patientId;

    @OneToOne(mappedBy = "appointmentPatientRecord")
    @JsonIgnore
    private Prescription prescriptions;


}
