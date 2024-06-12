package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppointmentRecordIssues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer appointmentRecordIssuesId;

    private String description;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "dentalIssuesId")
    private DentalIssues dentalIssues;

    @ManyToOne
    @JoinColumn(name = "appointmentPatientRecordId")
    private AppointmentPatientRecord appointmentPatientRecord;
}
