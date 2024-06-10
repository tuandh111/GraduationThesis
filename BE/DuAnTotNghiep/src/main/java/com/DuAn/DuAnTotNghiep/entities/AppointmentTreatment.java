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
public class AppointmentTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentTreatmentId;

    private String description;

    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "appointmentPatientRecordId")
    private AppointmentPatientRecord appointmentPatientRecord;
}
