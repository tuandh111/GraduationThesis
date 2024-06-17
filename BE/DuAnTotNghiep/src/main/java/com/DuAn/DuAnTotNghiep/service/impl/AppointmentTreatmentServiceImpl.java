package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentTreatment;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentPatientRecordRepository;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentTreatmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentTreatmentServiceImpl implements AppointmentTreatmentService {
    @Autowired
    AppointmentTreatmentRepository appointmentTreatmentRepository;
    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;
    @Autowired
    TreatmentRepository treatmentRepository;

    @Override
    public AppointmentTreatment findByAppointmentTreatmentId(int appointmentTreatmentId) {
        return appointmentTreatmentRepository.findById(appointmentTreatmentId).orElseThrow(null);
    }

    @Override
    public List<AppointmentTreatment> findAllAppointmentTreatment() {
        return appointmentTreatmentRepository.findAll();
    }

    @Override
    public List<AppointmentTreatment> findAllAppointmentTreatmentExceptDeleted() {
        return appointmentTreatmentRepository.findAll().stream()
                .filter(appointmentTreatment -> !appointmentTreatment.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentTreatment saveAppointmentTreatment(AppointmentTreatmentRequest appointmentTreatmentRequest) {
        AppointmentTreatment appointmentTreatment = AppointmentTreatment
                                                            .builder()
                                                            .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentTreatmentRequest.getAppointPatientRecordId()).orElse(null))
                                                            .treatment(treatmentRepository.findById(appointmentTreatmentRequest.getTreatmentId()).orElse(null))
                                                            .description(appointmentTreatmentRequest.getDescription())
                                                            .build();
        appointmentTreatmentRepository.save(appointmentTreatment);
        return appointmentTreatment;
    }

    @Override
    public AppointmentTreatment updateAppointmentTreatment(int appointmentTreatmentId, AppointmentTreatmentRequest appointmentTreatmentRequest) {
        AppointmentTreatment appointmentTreatment = AppointmentTreatment
                                                            .builder()
                                                            .appointmentTreatmentId(appointmentTreatmentId)
                                                            .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentTreatmentRequest.getAppointPatientRecordId()).orElseThrow(null))
                                                            .treatment(treatmentRepository.findById(appointmentTreatmentRequest.getTreatmentId()).orElseThrow(null))
                                                            .description(appointmentTreatmentRequest.getDescription())
                                                            .build();
        appointmentTreatmentRepository.save(appointmentTreatment);
        return appointmentTreatment;
    }

    @Override
    public MessageResponse delete(int appointmentTreatmentId) {
        try {
            appointmentTreatmentRepository.deleteById(appointmentTreatmentId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteAppointmentTreatment(int appointmentTreatmentId) {
        try {
            AppointmentTreatment appointmentTreatment = AppointmentTreatment
                                                                .builder()
                                                                .appointmentTreatmentId(appointmentTreatmentId)
                                                                .isDeleted(true)
                                                                .build();
            appointmentTreatmentRepository.save(appointmentTreatment);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
