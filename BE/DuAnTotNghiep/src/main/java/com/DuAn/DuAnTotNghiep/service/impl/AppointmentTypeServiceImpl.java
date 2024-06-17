package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentTypeRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {
    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public AppointmentType findByAppointmentTypeId(int appointmentTypeId) {
        return appointmentTypeRepository.findById(appointmentTypeId).orElseThrow(null);
    }

    @Override
    public List<AppointmentType> findAll() {
        return appointmentTypeRepository.findAll().stream()
                       .filter(appointmentType -> !appointmentType.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public AppointmentType saveAppointmentType(AppointmentTypeRequest appointmentTypeRequest) {
        var appointmentType = AppointmentType
                                      .builder()
                                      .typeName(appointmentTypeRequest.getType())
                                      .description(appointmentTypeRequest.getDescription())
                                      .build();
        appointmentTypeRepository.save(appointmentType);
        return appointmentType;
    }

    @Override
    public AppointmentType updateAppointmentType(int appointmentTypeId, AppointmentTypeRequest appointmentTypeRequest) {
        var appointmentType = AppointmentType
                                      .builder()
                                      .appointment_TypeId(appointmentTypeId)
                                      .typeName(appointmentTypeRequest.getType())
                                      .description(appointmentTypeRequest.getDescription())
                                      .build();
        appointmentTypeRepository.save(appointmentType);
        return appointmentType;
    }

    @Override
    public MessageResponse delete(int appointmentTypeId) {
        try {
            appointmentTypeRepository.deleteById(appointmentTypeId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse sortDeleteAppointmentType(int appointmentTypeId) {
        try {
            var appointmentType = AppointmentType
                                          .builder()
                                          .appointment_TypeId(appointmentTypeId)
                                          .isDeleted(true)
                                          .build();
            appointmentTypeRepository.save(appointmentType);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
