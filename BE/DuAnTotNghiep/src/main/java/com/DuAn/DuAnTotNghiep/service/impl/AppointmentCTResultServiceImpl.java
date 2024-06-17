package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentCTResult;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentCTResultRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentCTResultRepository;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.DentalStaffRepository;
import com.DuAn.DuAnTotNghiep.repositories.ImagingPlanesRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentCTResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentCTResultServiceImpl implements AppointmentCTResultService {
    @Autowired
    AppointmentCTResultRepository appointmentCTResultRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DentalStaffRepository dentalStaffRepository;
    @Autowired
    ImagingPlanesRepository imagingPlanesRepository;

    @Override
    public AppointmentCTResult findByAppointmentCTResultId(int appointmentCTResultId) {
        return appointmentCTResultRepository.findById(appointmentCTResultId).orElseThrow(null);
    }

    @Override
    public List<AppointmentCTResult> findAll() {
        return  appointmentCTResultRepository.findAll();
    }

    @Override
    public List<AppointmentCTResult> findAllExceptDeleted() {
        return  appointmentCTResultRepository.findAll()
                .stream()
                .filter(appointmentCTResult -> !appointmentCTResult.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentCTResult saveAppointmentCTResult(AppointmentCTResultRequest appointmentCTResultRequest) {
        var appointmentCTResult = AppointmentCTResult.builder()
                .imageURL(appointmentCTResultRequest.getImage())
                .dentalStaff(dentalStaffRepository.findById(appointmentCTResultRequest.getDentalStaffId()).orElse(null))
                .appointment(appointmentRepository.findById(appointmentCTResultRequest.getAppointmentId()).orElse(null))
                .imagingPlanes(imagingPlanesRepository.findById(appointmentCTResultRequest.getImagingPlanesId()).orElse(null))
                .date(appointmentCTResultRequest.getDate())
                .build();
        appointmentCTResultRepository.save(appointmentCTResult);
        return appointmentCTResult;
    }

    @Override
    public AppointmentCTResult updateAppointmentCTResult(int appointmentCTResultId, AppointmentCTResultRequest appointmentCTResultRequest) {
        var appointmentCTResult = AppointmentCTResult.builder().appointmentCTResultId(appointmentCTResultId).imageURL(appointmentCTResultRequest.getImage()).dentalStaff(dentalStaffRepository.findById(appointmentCTResultRequest.getDentalStaffId()).orElseThrow(null)).appointment(appointmentRepository.findById(appointmentCTResultRequest.getAppointmentId()).orElse(null)).imagingPlanes(imagingPlanesRepository.findById(appointmentCTResultRequest.getImagingPlanesId()).orElse(null)).date(appointmentCTResultRequest.getDate()).build();
        appointmentCTResultRepository.save(appointmentCTResult);
        return appointmentCTResult;
    }

    @Override
    public MessageResponse deleteById(int appointmentCTResultId) {
        try {
            appointmentCTResultRepository.deleteById(appointmentCTResultId);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteAppointmentCTResult(int appointmentCTResultId) {
        try {
            var appointmentCTResult = AppointmentCTResult
                                              .builder()
                                              .appointmentCTResultId(appointmentCTResultId)
                                              .isDeleted(true)
                                              .build();
            appointmentCTResultRepository.save(appointmentCTResult);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
