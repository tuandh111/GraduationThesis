package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.PrescriptionRepository;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription findByPrescriptionId(int prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).orElse(null);
    }

    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription savePrescription(PrescriptionRequest prescriptionRequest) {
        Prescription prescription = Prescription.builder()
                .description(prescriptionRequest.getDescription())
                .build();
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription updatePrescription(int prescriptionId, PrescriptionRequest prescriptionRequest) {
        Prescription prescription = Prescription.builder()
                .prescriptionId(prescriptionId)
                .description(prescriptionRequest.getDescription())
                .build();
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public MessageResponse deletePrescription(int prescriptionId) {
        try {
            prescriptionRepository.deleteById(prescriptionId);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }

    @Override
    public MessageResponse softDeletePrescription(int prescriptionId) {
        try {
            Prescription prescription = Prescription.builder()
                    .prescriptionId(prescriptionId)
                    .isDeleted(true)
                    .build();
            prescriptionRepository.save(prescription);
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }
}
