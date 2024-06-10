package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PrescriptionMedicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescriptionMedicinesId;

    private String prescriptionMedicines;

    private boolean isDeleted = false;

    private String frequency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescriptionId")
    private Prescription prescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicinesId")
    private Medicines medicines;

}
