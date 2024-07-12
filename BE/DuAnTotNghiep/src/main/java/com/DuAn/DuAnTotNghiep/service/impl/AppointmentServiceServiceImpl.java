package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentService;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentServiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentServiceRepository;
import com.DuAn.DuAnTotNghiep.repositories.ServiceRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceServiceImpl implements AppointmentServiceService {
    @Autowired
    AppointmentServiceRepository appointmentServiceRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public AppointmentService findByAppointmentServiceId(int appointmentId) {
        return appointmentServiceRepository.findById(appointmentId).orElseThrow(null);
    }

    @Override
    public List<AppointmentService> findAllAppointmentService() {
        return appointmentServiceRepository.findAll();
    }

    @Override
    public List<AppointmentService> findAllAppointmentServiceExceptDeleted() {
        return appointmentServiceRepository.findAll().stream()
                .filter(appointmentService -> !appointmentService.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentService saveAppointmentService(AppointmentServiceRequest appointmentServiceRequest) {
        AppointmentService appointmentService = AppointmentService
                                                        .builder()
                                                        .appointment(appointmentRepository.findById(appointmentServiceRequest.getAppointmentId()).orElse(null))
                                                        .service(serviceRepository.findById(appointmentServiceRequest.getServiceId()).orElse(null))
                                                        .quantity(appointmentServiceRequest.getQuantity())
                                                        .price(appointmentServiceRequest.getPrice())
                                                        .build();
        appointmentServiceRepository.save(appointmentService);
        return appointmentService;
    }

    @Override
    public AppointmentService updateAppointmentService(int appointmentServiceId, AppointmentServiceRequest appointmentServiceRequest) {
        AppointmentService appointmentService = AppointmentService
                                                        .builder()
                                                        .appointment_ServiceId(appointmentServiceId)
                                                        .appointment(appointmentRepository.findById(appointmentServiceRequest.getAppointmentId()).orElse(null))
                                                        .service(serviceRepository.findById(appointmentServiceRequest.getServiceId()).orElse(null))
                                                        .quantity(appointmentServiceRequest.getQuantity())
                                                        .price(appointmentServiceRequest.getPrice())
                                                        .build();
        appointmentServiceRepository.save(appointmentService);
        return appointmentService;
    }

    @Override
    public MessageResponse delete(int appointmentServiceId) {
        try {
            appointmentServiceRepository.deleteById(appointmentServiceId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteAppointmentService(int appointmentServiceId) {
        try {
            AppointmentService appointmentService = appointmentServiceRepository.findById(appointmentServiceId)
                                                            .orElseThrow(() -> new RuntimeException("appointment Service not found"));
            appointmentService.setDeleted(true);
            appointmentServiceRepository.save(appointmentService);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public List<AppointmentService> findAppointmentServiceByAppId(Integer appointmentId) {
        return appointmentServiceRepository.getAppointmentServiceByAppId(appointmentId);
    }
}
