package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.entities.Medicine;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicineService {
    Medicine findByMedicineId(int medicineId) ;

    List<Medicine> findAllMedicines() ;

    Medicine saveMedicine(MedicineRequest medicineRequest) ;

    Medicine updateMedicine(int medicineId, MedicineRequest medicineRequest) ;

    MessageResponse deleteMedicine(int medicineId) ;

    MessageResponse softDeleteMedicine(int medicineId) ;
}

