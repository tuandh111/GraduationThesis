package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescriptionId;

    private String description;

    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "appointmentPatientRecordId")
    private AppointmentPatientRecord appointmentPatientRecord;

    @ManyToOne
    @JoinColumn(name = "treatmentDuration")
    private TreatmentDuration treatmentDuration;

    @OneToMany(mappedBy = "prescriptionMedicinesId")
    @JsonIgnore
    private List<PrescriptionMedicines> prescriptionMedicines;
}
