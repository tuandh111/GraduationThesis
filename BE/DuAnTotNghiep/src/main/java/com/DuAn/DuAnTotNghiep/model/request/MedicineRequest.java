package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

@Data
public class MedicineRequest {
    private String medicineName;

    private boolean beforeEating;

    private Integer medicinesDosageUnitId;

    private Integer distributionMedicinesId;

    private Integer medicinesDosageAmountId;

    private Integer medicineCategoryId;
}
