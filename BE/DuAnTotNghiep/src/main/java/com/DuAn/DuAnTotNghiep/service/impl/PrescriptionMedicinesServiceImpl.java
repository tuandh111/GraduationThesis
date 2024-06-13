package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.PrescriptionMedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicineService;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionMedicinesService;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionMedicinesServiceImpl implements PrescriptionMedicinesService {

    @Autowired
    private PrescriptionMedicinesRepository prescriptionMedicinesRepository;

    @Autowired
    private PrescriptionService prescriptionService ;

    @Autowired
    private MedicineService medicineService ;

    @Override
    public PrescriptionMedicines findPrescriptionMedicinesById(int prescriptionMedicinesId) {
        return prescriptionMedicinesRepository.findById(prescriptionMedicinesId).orElse(null);
    }

    @Override
    public List<PrescriptionMedicines> findAllPrescriptionMedicines() {
        return prescriptionMedicinesRepository.findAll();
    }

    @Override
    public PrescriptionMedicines savePrescriptionMedicines(PrescriptionMedicinesRequest prescriptionMedicinesRequest) {
        PrescriptionMedicines prescriptionMedicines = PrescriptionMedicines.builder()
                .prescriptionMedicines(prescriptionMedicinesRequest.getPrescriptionMedicines())
                .frequency(prescriptionMedicinesRequest.getFrequency())
//                .prescription(prescriptionService.findByPrescriptionId(prescriptionMedicinesRequest.getPrescriptionId() != null ? prescriptionMedicinesRequest.getPrescriptionId() : null))
//                .medicines(medicineService.findByMedicineId(prescriptionMedicinesRequest.getMedicinesId() != null ? prescriptionMedicinesRequest.getMedicinesId() : null))
                .build();
        prescriptionMedicinesRepository.save(prescriptionMedicines);
        return prescriptionMedicines;
    }

    @Override
    public PrescriptionMedicines updatePrescriptionMedicines(int prescriptionMedicinesId, PrescriptionMedicinesRequest prescriptionMedicinesRequest) {
        PrescriptionMedicines prescriptionMedicines = PrescriptionMedicines.builder()
                .prescriptionMedicinesId(prescriptionMedicinesId)
                .prescriptionMedicines(prescriptionMedicinesRequest.getPrescriptionMedicines())
                .frequency(prescriptionMedicinesRequest.getFrequency())
//                .prescription(prescriptionService.findByPrescriptionId(prescriptionMedicinesRequest.getPrescriptionId() != null ? prescriptionMedicinesRequest.getPrescriptionId() : null))
//                .medicines(medicineService.findByMedicineId(prescriptionMedicinesRequest.getMedicinesId() != null ? prescriptionMedicinesRequest.getMedicinesId() : null))
                .build() ;
        prescriptionMedicinesRepository.save(prescriptionMedicines) ;
        return prescriptionMedicines;
    }

    @Override
    public MessageResponse deletePrescriptionMedicines(int prescriptionMedicinesId) {
        try {
            prescriptionMedicinesRepository.deleteById(prescriptionMedicinesId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeletePrescriptionMedicines(int prescriptionMedicinesId) {
        try {
            PrescriptionMedicines prescriptionMedicines = prescriptionMedicinesRepository.findById(prescriptionMedicinesId).orElseThrow(null) ;
            prescriptionMedicines.setDeleted(true) ;
            prescriptionMedicinesRepository.save(prescriptionMedicines) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return new MessageResponse("Failed") ;
        }
    }
}
