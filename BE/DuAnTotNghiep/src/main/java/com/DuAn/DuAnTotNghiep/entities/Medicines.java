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
public class Medicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicinesId;

    private String medicineName;

    private String beforeEating;

    @OneToMany(mappedBy = "prescriptionMedicinesId")
    @JsonIgnore
    private List<PrescriptionMedicines> prescriptionMedicines;
}
