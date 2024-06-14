package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageUnit;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageUnitRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicinesDosageUnitService {
    MedicinesDosageUnit findMedicinesDosageUnitById(int dosageUnitId) ;

    List<MedicinesDosageUnit> findAllMedicinesDosageUnits() ;

    MedicinesDosageUnit saveMedicinesDosageUnit(MedicinesDosageUnitRequest dosageUnitRequest) ;

    MedicinesDosageUnit updateMedicinesDosageUnit(int dosageUnitId, MedicinesDosageUnitRequest dosageUnitRequest) ;

    MessageResponse deleteMedicinesDosageUnit(int dosageUnitId) ;

    MessageResponse softDeleteMedicinesDosageUnit(int dosageUnitId) ;
}
