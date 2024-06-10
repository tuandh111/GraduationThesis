package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentPatientRecord;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentPatientRecordRepository;
import com.DuAn.DuAnTotNghiep.repositories.PatientRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentPatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentPatientRecordServiceImpl implements AppointmentPatientRecordService {
    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public AppointmentPatientRecord findByAppointmentPatientRecordId(int appointmentPatientRecordId) {
        return appointmentPatientRecordRepository.findById(appointmentPatientRecordId).orElseThrow(null);
    }

    @Override
    public List<AppointmentPatientRecord> findAllAppointmentPatientRecord() {
        return appointmentPatientRecordRepository.findAll();
    }

    @Override
    public AppointmentPatientRecord saveAppointmentPatientRecord(AppointmentPatientRecordRequest appointmentPatientRecordRequest) {
        AppointmentPatientRecord appointmentPatientRecord = AppointmentPatientRecord
                                                                    .builder()
                                                                    .patient(patientRepository.findById(appointmentPatientRecordRequest.getPatientId()).orElseThrow(null))
                                                                    .createAt(appointmentPatientRecordRequest.getCreateAt())
                                                                    .currentCodition(appointmentPatientRecordRequest.getCurrentCondition())
                                                                    .reExamination(appointmentPatientRecordRequest.getReExamination())
                                                                    .build();
        appointmentPatientRecordRepository.save(appointmentPatientRecord);
        return appointmentPatientRecord;
    }

    @Override
    public AppointmentPatientRecord updateAppointmentPatientRecord(int appointmentPatientRecordId, AppointmentPatientRecordRequest appointmentPatientRecordRequest) {
        AppointmentPatientRecord appointmentPatientRecord = AppointmentPatientRecord
                                                                    .builder()
                                                                    .appointmentPatientRecordId(appointmentPatientRecordId)
                                                                    .patient(patientRepository.findById(appointmentPatientRecordRequest.getPatientId()).orElseThrow(null))
                                                                    .createAt(appointmentPatientRecordRequest.getCreateAt())
                                                                    .currentCodition(appointmentPatientRecordRequest.getCurrentCondition())
                                                                    .reExamination(appointmentPatientRecordRequest.getReExamination())
                                                                    .build();
        appointmentPatientRecordRepository.save(appointmentPatientRecord);
        return appointmentPatientRecord;
    }

    @Override
    public MessageResponse delete(int appointmentPatientRecordId) {
       try {
           appointmentPatientRecordRepository.deleteById(appointmentPatientRecordId);
           return  new MessageResponse("successfully");
       }catch (Exception e){
           e.printStackTrace();
           return new MessageResponse("fail");
       }
    }

    @Override
    public MessageResponse sortDeleteAppointmentType(int appointmentPatientRecordId) {
        try {
            AppointmentPatientRecord appointmentPatientRecord = AppointmentPatientRecord
                                                                        .builder()
                                                                        .appointmentPatientRecordId(appointmentPatientRecordId)
                                                                        .isDeleted(true)
                                                                        .build();
            appointmentPatientRecordRepository.save(appointmentPatientRecord);
            return  new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
