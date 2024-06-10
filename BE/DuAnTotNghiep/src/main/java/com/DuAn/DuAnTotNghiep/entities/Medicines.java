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

    private boolean beforeEating;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "prescriptionMedicinesId")
    @JsonIgnore
    private List<PrescriptionMedicines> prescriptionMedicines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicinesDosageUnitId")
    private MedicinesDosageUnit medicinesDosageUnit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributionMedicinesId")
    private DistributionMedicines  distributionMedicines;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "medicinesDosageAmountId")
    private MedicinesDosageAmount medicinesDosageAmount;

    @OneToMany(mappedBy = "frequencyMedicinesId")
    @JsonIgnore
    private List<FrequencyMedicines> frequencyMedicines;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "medicineCategoryId")
    private MedicineCategory medicineCategory;
}
