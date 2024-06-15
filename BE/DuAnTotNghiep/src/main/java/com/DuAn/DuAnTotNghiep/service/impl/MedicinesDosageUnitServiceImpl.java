package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageUnit;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageUnitRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesDosageUnitRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return medicinesDosageUnitRepository.findAll().stream()
                       .filter(medicinesDosageUnit -> !medicinesDosageUnit.isDeleted())
                       .collect(Collectors.toList());
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
            medicinesDosageUnitRepository.deleteById(dosageUnitId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteMedicinesDosageUnit(int dosageUnitId) {
        try {
            MedicinesDosageUnit medicinesDosageUnit = medicinesDosageUnitRepository.findById(dosageUnitId).orElseThrow(null) ;
            medicinesDosageUnit.setDeleted(true) ;
            medicinesDosageUnitRepository.save(medicinesDosageUnit) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }
}
