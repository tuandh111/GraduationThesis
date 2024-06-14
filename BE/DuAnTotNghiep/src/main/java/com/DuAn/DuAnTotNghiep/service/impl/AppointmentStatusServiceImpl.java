package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentStatusRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentStatusServiceImpl implements AppointmentStatusService {
    @Autowired
    AppointmentStatusRepository appointmentStatusRepository;


    @Override
    public AppointmentStatus findByAppointmentStatusId(int appointmentStatusId) {
        return appointmentStatusRepository.findById(appointmentStatusId).orElseThrow(null);
    }

    @Override
    public List<AppointmentStatus> findAll() {
        return appointmentStatusRepository.findAll();
    }

    @Override
    public AppointmentStatus saveAppointmentStatus(AppointmentStatusRequest appointmentStatusRequest) {
        var appointmentStatus = AppointmentStatus
                                  .builder()
                                  .status(appointmentStatusRequest.getStatus())
                                        .description(appointmentStatusRequest.getDescription())
                                        .build();
        appointmentStatusRepository.save(appointmentStatus);
        return appointmentStatus;
    }

    @Override
    public AppointmentStatus updateAppointmentStatus(int appointmentStatusId, AppointmentStatusRequest appointmentStatusRequest) {
        var appointmentStatus = AppointmentStatus
                                        .builder()
                                        .appointment_StatusId(appointmentStatusId)
                                        .status(appointmentStatusRequest.getStatus())
                                        .description(appointmentStatusRequest.getDescription())
                                        .build();
        appointmentStatusRepository.save(appointmentStatus);

        return appointmentStatus;
    }

    @Override
    public MessageResponse delete(int appointmentStatusId) {
        try {
            appointmentStatusRepository.deleteById(appointmentStatusId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteAppointmentStatus(int appointmentStatusId) {
        try {
            var appointmentStatus = AppointmentStatus
                                            .builder()
                                            .appointment_StatusId(appointmentStatusId)
                                            .isDeleted(true)
                                            .build();
            appointmentStatusRepository.save(appointmentStatus);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
