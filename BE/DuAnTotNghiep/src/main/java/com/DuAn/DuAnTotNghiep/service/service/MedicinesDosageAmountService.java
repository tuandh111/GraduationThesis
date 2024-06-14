package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageAmount;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageAmountRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicinesDosageAmountService {
    MedicinesDosageAmount findByMedicinesDosageAmountId(int dosageAmountId) ;

    List<MedicinesDosageAmount> findAllMedicinesDosageAmounts() ;

    MedicinesDosageAmount saveMedicinesDosageAmount(MedicinesDosageAmountRequest dosageAmountRequest) ;

    MedicinesDosageAmount updateMedicinesDosageAmount(int dosageAmountId, MedicinesDosageAmountRequest dosageAmountRequest) ;

    MessageResponse deleteMedicinesDosageAmount(int dosageAmountId) ;

    MessageResponse softDeleteMedicinesDosageAmount(int dosageAmountId) ;
}
