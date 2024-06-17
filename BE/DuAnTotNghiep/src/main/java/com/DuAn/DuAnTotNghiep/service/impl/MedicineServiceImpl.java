package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.*;
import com.DuAn.DuAnTotNghiep.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// còn lỗi get và get by id
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicinesRepository medicineRepository ;

    @Autowired
    private MedicinesDosageUnitRepository medicinesDosageUnitRepository ;

    @Autowired
    private DistributionMedicinesRepository distributionMedicinesRepository ;

    @Autowired
    private MedicinesDosageAmountRepository medicinesDosageAmountRepository ;

    @Autowired
    private MedicinesCategoryRepository medicinesCategoryRepository ;

    @Override
    public Medicines findByMedicineId(int medicineId) {
        return medicineRepository.findById(medicineId).orElse(null) ;

    }

    @Override
    public List<Medicines> findAllMedicines() {
        return medicineRepository.findAll() ;
    }

    @Override
    public List<Medicines> findAllMedicinesExceptDeleted() {
        return medicineRepository.findAll().stream()
                .filter(medicines -> !medicines.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Medicines saveMedicine(MedicineRequest medicineRequest) {
        var medicine = Medicines.builder()
                .medicineName(medicineRequest.getMedicineName())
                .beforeEating(medicineRequest.isBeforeEating())
                .medicinesDosageUnit(medicinesDosageUnitRepository.findById(medicineRequest.getMedicinesDosageUnitId()).orElse(null))
                .distributionMedicines(distributionMedicinesRepository.findById(medicineRequest.getDistributionMedicinesId()).orElse(null))
                .medicinesDosageAmount(medicinesDosageAmountRepository.findById(medicineRequest.getMedicinesDosageAmountId()).orElse(null))
                .medicineCategory(medicinesCategoryRepository.findById(medicineRequest.getMedicineCategoryId()).orElse(null))
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
                .medicinesDosageUnit(medicinesDosageUnitRepository.findById(medicineRequest.getMedicinesDosageUnitId()).orElse(null))
                .distributionMedicines(distributionMedicinesRepository.findById(medicineRequest.getDistributionMedicinesId()).orElse(null))
                .medicinesDosageAmount(medicinesDosageAmountRepository.findById(medicineRequest.getMedicinesDosageAmountId()).orElse(null))
                .medicineCategory(medicinesCategoryRepository.findById(medicineRequest.getMedicineCategoryId()).orElse(null))
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
