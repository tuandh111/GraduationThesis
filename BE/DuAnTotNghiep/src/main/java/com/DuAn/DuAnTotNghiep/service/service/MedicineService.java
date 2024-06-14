package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicineService {
    Medicines findByMedicineId(int medicineId) ;

    List<Medicines> findAllMedicines() ;

    Medicines saveMedicine(MedicineRequest medicineRequest) ;

    Medicines updateMedicine(int medicineId, MedicineRequest medicineRequest) ;

    MessageResponse deleteMedicine(int medicineId) ;

    MessageResponse softDeleteMedicine(int medicineId) ;

}

