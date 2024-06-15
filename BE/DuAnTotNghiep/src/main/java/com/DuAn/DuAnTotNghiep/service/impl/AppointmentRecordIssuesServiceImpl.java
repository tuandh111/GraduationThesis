package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentRecordIssues;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRecordIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentPatientRecordRepository;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRecordIssuesRepository;
import com.DuAn.DuAnTotNghiep.repositories.DentalIssuesRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentRecordIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentRecordIssuesServiceImpl implements AppointmentRecordIssuesService {
    @Autowired
    AppointmentRecordIssuesRepository appointmentRecordIssuesRepository;

    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;
    @Autowired
    DentalIssuesRepository dentalIssuesRepository;

    @Override
    public AppointmentRecordIssues findByAppointmentRecordIssuesId(int appointmentRecordIssuesId) {
        return appointmentRecordIssuesRepository.findById(appointmentRecordIssuesId).orElseThrow(null);
    }

    @Override
    public List<AppointmentRecordIssues> findAllAppointmentRecordIssues() {
        return appointmentRecordIssuesRepository.findAll()
                       .stream()
                       .filter(appointmentRecordIssues -> !appointmentRecordIssues.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public AppointmentRecordIssues saveAppointmentRecordIssues(AppointmentRecordIssuesRequest appointmentRecordIssuesRequest) {
        AppointmentRecordIssues appointmentRecordIssues = AppointmentRecordIssues
                                                                  .builder()
                                                                  .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRecordIssuesRequest.getAppointmentPatientRecordId()).orElse(null))
                                                                  .dentalIssues(dentalIssuesRepository.findById(appointmentRecordIssuesRequest.getDentalIssuesId()).orElse(null))
                                                                  .description(appointmentRecordIssuesRequest.getDescription())
                                                                  .build();
        appointmentRecordIssuesRepository.save(appointmentRecordIssues);
        return appointmentRecordIssues;
    }

    @Override
    public AppointmentRecordIssues updateAppointmentRecordIssues(int appointmentRecordIssuesId, AppointmentRecordIssuesRequest appointmentRecordIssuesRequest) {
        AppointmentRecordIssues appointmentRecordIssues = AppointmentRecordIssues
                                                                  .builder()
                                                                  .appointmentRecordIssuesId(appointmentRecordIssuesId)
                                                                  .appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRecordIssuesRequest.getAppointmentPatientRecordId()).orElse(null))
                                                                  .dentalIssues(dentalIssuesRepository.findById(appointmentRecordIssuesRequest.getDentalIssuesId()).orElse(null))
                                                                  .description(appointmentRecordIssuesRequest.getDescription())
                                                                  .build();
        appointmentRecordIssuesRepository.save(appointmentRecordIssues);
        return appointmentRecordIssues;
    }

    @Override
    public MessageResponse delete(int appointmentRecordIssuesId) {
       try {
           appointmentRecordIssuesRepository.deleteById(appointmentRecordIssuesId);
           return new MessageResponse("successfully");
       }catch (Exception e){
           e.printStackTrace();
           return new MessageResponse("fail");
       }
    }

    @Override
    public MessageResponse sortDeleteAppointmentRecordIssues(int appointmentRecordIssuesId) {
        try {
            AppointmentRecordIssues appointmentRecordIssues = AppointmentRecordIssues
                                                                      .builder()
                                                                      .appointmentRecordIssuesId(appointmentRecordIssuesId)
                                                                      .isDeleted(true)
                                                                      .build();
            appointmentRecordIssuesRepository.save(appointmentRecordIssues);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
