package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Medicine;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicinesRepository medicineRepository;

    @Override
    public Medicine findByMedicineId(int medicineId) {
        return medicineRepository.findById(medicineId).orElse(null);
    }

    @Override
    public List<Medicine> findAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine saveMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = Medicine.builder()
                .medicineName(medicineRequest.getMedicineName())
                .beforeEating(medicineRequest.isBeforeEating())
                .build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public Medicine updateMedicine(int medicineId, MedicineRequest medicineRequest) {
        Medicine medicine = Medicine.builder()
                .medicinesId(medicineId)
                .medicineName(medicineRequest.getMedicineName())
                .beforeEating(medicineRequest.isBeforeEating())
                .build();
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public MessageResponse deleteMedicine(int medicineId) {
        try {
            medicineRepository.deleteById(medicineId);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }

    @Override
    public MessageResponse softDeleteMedicine(int medicineId) {
        try {
            Medicine medicine = Medicine.builder()
                    .medicinesId(medicineId)
                    .isDeleted(true)
                    .build();
            medicineRepository.save(medicine);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }
}
