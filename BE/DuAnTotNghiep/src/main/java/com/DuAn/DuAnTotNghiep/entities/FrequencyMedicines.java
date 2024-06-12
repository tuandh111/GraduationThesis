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
public class FrequencyMedicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer frequencyMedicinesId;

    private  String description;

    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicinesId")
    private Medicines medicines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequencyId")
    private Frequency frequency;
}
