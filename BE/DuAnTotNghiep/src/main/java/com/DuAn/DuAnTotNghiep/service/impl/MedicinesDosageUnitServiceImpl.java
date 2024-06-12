package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageUnit;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageUnitRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesDosageUnitRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinesDosageUnitServiceImpl implements MedicinesDosageUnitService {

    @Autowired
    private MedicinesDosageUnitRepository medicinesDosageUnitRepository;

    @Override
    public MedicinesDosageUnit findMedicinesDosageUnitById(int dosageUnitId) {
        return medicinesDosageUnitRepository.findById(dosageUnitId).orElse(null);
    }

    @Override
    public List<MedicinesDosageUnit> findAllMedicinesDosageUnits() {
        return medicinesDosageUnitRepository.findAll();
    }

    @Override
    public MedicinesDosageUnit saveMedicinesDosageUnit(MedicinesDosageUnitRequest dosageUnitRequest) {
        MedicinesDosageUnit dosageUnit = MedicinesDosageUnit.builder()
                .unit(dosageUnitRequest.getUnit())
                .description(dosageUnitRequest.getDescription())
                .build();
        medicinesDosageUnitRepository.save(dosageUnit);
        return dosageUnit;
    }

    @Override
    public MedicinesDosageUnit updateMedicinesDosageUnit(int dosageUnitId, MedicinesDosageUnitRequest dosageUnitRequest) {
        MedicinesDosageUnit dosageUnit = MedicinesDosageUnit.builder()
                .medicinesDosageUnitId(dosageUnitId)
                .unit(dosageUnitRequest.getUnit())
                .description(dosageUnitRequest.getDescription())
                .build();
        medicinesDosageUnitRepository.save(dosageUnit);
        return dosageUnit;
    }

    @Override
    public MessageResponse deleteMedicinesDosageUnit(int dosageUnitId) {
        try {
            medicinesDosageUnitRepository.deleteById(dosageUnitId);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }

    @Override
    public MessageResponse softDeleteMedicinesDosageUnit(int dosageUnitId) {
        try {
            MedicinesDosageUnit dosageUnit = MedicinesDosageUnit.builder()
                    .medicinesDosageUnitId(dosageUnitId)
                    .isDeleted(true)
                    .build();
            medicinesDosageUnitRepository.save(dosageUnit);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }
}
