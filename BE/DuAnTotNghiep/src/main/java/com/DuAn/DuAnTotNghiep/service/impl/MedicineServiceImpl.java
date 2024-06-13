package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.*;
import com.DuAn.DuAnTotNghiep.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// còn lỗi get và get by id
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicinesRepository medicineRepository ;

    @Autowired
    private MedicinesDosageUnitService medicinesDosageUnitService ;

    @Autowired
    private DistributionMedicinesService distributionMedicinesService ;

    @Autowired
    private MedicinesDosageAmountService medicinesDosageAmountService ;

    @Autowired
    private MedicinesCategoryService medicinesCategoryService ;

    @Override
    public Medicines findByMedicineId(int medicineId) {
        return medicineRepository.findById(medicineId).orElse(null) ;

    }

    @Override
    public List<Medicines> findAllMedicines() {
        return medicineRepository.findAll() ;
    }

    @Override
    public Medicines saveMedicine(MedicineRequest medicineRequest) {
        var medicine = Medicines.builder()
                .medicineName(medicineRequest.getMedicineName())
                .beforeEating(medicineRequest.isBeforeEating())
                .medicinesDosageUnit(medicinesDosageUnitService.findMedicinesDosageUnitById(medicineRequest.getMedicinesDosageUnitId()))
                .distributionMedicines(distributionMedicinesService.findByDistributionMedicinesId(medicineRequest.getDistributionMedicinesId()))
                .medicinesDosageAmount(medicinesDosageAmountService.findByMedicinesDosageAmountId(medicineRequest.getMedicinesDosageAmountId()))
                .medicineCategory(medicinesCategoryService.findByMedicineCategoryId(medicineRequest.getMedicineCategoryId()))
                .build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public Medicines updateMedicine(int medicineId, MedicineRequest medicineRequest) {
        var medicine = Medicines.builder()
                .medicinesId(medicineId)
                .medicineName(medicineRequest.getMedicineName())
                .beforeEating(medicineRequest.isBeforeEating())
                .medicinesDosageUnit(medicinesDosageUnitService.findMedicinesDosageUnitById(medicineRequest.getMedicinesDosageUnitId()))
                .distributionMedicines(distributionMedicinesService.findByDistributionMedicinesId(medicineRequest.getDistributionMedicinesId()))
                .medicinesDosageAmount(medicinesDosageAmountService.findByMedicinesDosageAmountId(medicineRequest.getMedicinesDosageAmountId()))
                .medicineCategory(medicinesCategoryService.findByMedicineCategoryId(medicineRequest.getMedicineCategoryId()))
                .build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public MessageResponse deleteMedicine(int medicineId) {
        try {
            medicineRepository.deleteById(medicineId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteMedicine(int medicineId) {
        try {
            Medicines medicine = medicineRepository.findById(medicineId).orElseThrow(null) ;
            medicine.setDeleted(true) ;
            medicineRepository.save(medicine) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }

}
