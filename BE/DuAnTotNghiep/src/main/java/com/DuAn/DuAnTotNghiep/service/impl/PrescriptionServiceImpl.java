package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.model.response.PrescriptionWithMedicinesResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentPatientRecordRepository;
import com.DuAn.DuAnTotNghiep.repositories.PrescriptionRepository;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentDurationRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentPatientRecordService;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionService;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// còn lỗi get và get by id
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository ;

    @Autowired
    private AppointmentPatientRecordRepository appointmentPatientRecordRepository ;

    @Autowired
    private TreatmentDurationRepository treatmentDurationRepository ;
    @Override
    public Prescription findByPrescriptionId(int prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).orElse(null);
    }

    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll() ;
    }

    @Override
    public List<Prescription> findAllPrescriptionsExceptDeleted() {
        return prescriptionRepository.findAll().stream()
                .filter(prescription -> !prescription.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Prescription savePrescription(PrescriptionRequest prescriptionRequest) {
        Prescription prescription = Prescription.builder()
                .description(prescriptionRequest.getDescription())
                .appointmentPatientRecord(appointmentPatientRecordRepository.findById(prescriptionRequest.getAppointmentPatientRecordId()).orElse(null))
                .treatmentDuration(treatmentDurationRepository.findById(prescriptionRequest.getTreatmentDurationId()).orElse(null))
                .build();
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription updatePrescription(int prescriptionId, PrescriptionRequest prescriptionRequest) {
        Prescription prescription = Prescription.builder()
                .prescriptionId(prescriptionId)
                .description(prescriptionRequest.getDescription())
                .appointmentPatientRecord(appointmentPatientRecordRepository.findById(prescriptionRequest.getAppointmentPatientRecordId()).orElse(null))
                .treatmentDuration(treatmentDurationRepository.findById(prescriptionRequest.getTreatmentDurationId()).orElse(null))
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
            Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new RuntimeException("role not found")) ;
            prescription.setDeleted(true) ;
            prescriptionRepository.save(prescription) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public List<PrescriptionWithMedicinesResponse> findPrescriptionByAppointment(Integer appId) {
        //return prescriptionRepository.getPrescriptionByAppointment(appId);
        List<Prescription> prescriptions =prescriptionRepository.getPrescriptions(appId);

        List<Integer> prescriptionIds =prescriptions.stream()
                .map(Prescription::getPrescriptionId).collect(Collectors.toList());

        List<PrescriptionMedicines> prescriptionMedicines=prescriptionRepository.getMedicinesByPrescriptionIds(prescriptionIds);

        Map<Integer, List<Medicines>> medicineMap = prescriptionMedicines.stream()
                .collect(Collectors.groupingBy(
                        pm -> pm.getPrescription().getPrescriptionId(),
                        Collectors.mapping(PrescriptionMedicines::getMedicines, Collectors.toList())
                ));


        return prescriptions.stream()
                .map(pr->new PrescriptionWithMedicinesResponse(pr,medicineMap.get(pr.getPrescriptionId()),prescriptionMedicines))
                .collect(Collectors.toList());

    }
}
