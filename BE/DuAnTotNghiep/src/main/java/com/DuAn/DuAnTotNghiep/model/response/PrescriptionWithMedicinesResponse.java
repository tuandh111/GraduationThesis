package com.DuAn.DuAnTotNghiep.model.response;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionWithMedicinesResponse {
    private Prescription prescription;
    private List<Medicines> medicinesList;
    private List<PrescriptionMedicines> prescriptionMedicinesList;
}
