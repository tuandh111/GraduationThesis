package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DentalSuppliesAppointment;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesAppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.DentalSuppliesAppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.DentalSuppliesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalSuppliesAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentalSuppliesAppointmentImpl implements DentalSuppliesAppointmentService {

    @Autowired
    DentalSuppliesAppointmentRepository dentalSuppliesAppointmentRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DentalSuppliesRepository dentalSuppliesRepository;

    @Override
    public DentalSuppliesAppointment findDentalSuppliesAppointmentById(Integer dentalSupplies_AppointmentId) {
        return dentalSuppliesAppointmentRepository.findById(dentalSupplies_AppointmentId).orElseThrow(null);
    }

    @Override
    public List<DentalSuppliesAppointment> findAllDentalSuppliesAppointmentExceptDeleted() {
        return dentalSuppliesAppointmentRepository.findAll().stream()
                .filter(dentalSuppliesAppointment -> !dentalSuppliesAppointment.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalSuppliesAppointment> findDentalSuppliesByAppointment(Integer appoinmentId) {
        return dentalSuppliesAppointmentRepository.getByAppointmentId(appoinmentId);
    }

    @Override
    public DentalSuppliesAppointment saveReq(DentalSuppliesAppointmentRequest req) {
        var dentalSuppliesAppointment=DentalSuppliesAppointment.builder()
                .appointment(appointmentRepository.findById(req.getAppointmentId()).orElseThrow(null))
                .dentalSupplies(dentalSuppliesRepository.findById(req.getDentalSupplyId()).orElseThrow(null))
                .quantity(req.getQuantity())
                .isDeleted(req.isDelete())
                .build();
        dentalSuppliesAppointmentRepository.save(dentalSuppliesAppointment);
        return dentalSuppliesAppointment;
    }

    @Override
    public DentalSuppliesAppointment updateReq(Integer dentalSupplies_AppointmentId, DentalSuppliesAppointmentRequest req) {
        var dentalSuppliesAppointment=dentalSuppliesAppointmentRepository.findById(dentalSupplies_AppointmentId).orElseThrow(null);
        if (req.getAppointmentId() >0) {
            var appointment = appointmentRepository.findById(req.getAppointmentId()).orElseThrow(null);
            dentalSuppliesAppointment.setAppointment(appointment);
        }
        if(req.getDentalSupplyId()>0){
            var dentalSupplies=dentalSuppliesRepository.findById(req.getDentalSupplyId()).orElseThrow(null);
            dentalSuppliesAppointment.setDentalSupplies(dentalSupplies);
        }
        if (req.getQuantity()>0){
            dentalSuppliesAppointment.setQuantity(req.getQuantity());
        }
        dentalSuppliesAppointment.setDeleted(req.isDelete());
        dentalSuppliesAppointmentRepository.save(dentalSuppliesAppointment);
        return dentalSuppliesAppointment;
    }

    @Override
    public MessageResponse deleteReq(Integer dentalSupplies_AppointmentId) {
        try {
            dentalSuppliesAppointmentRepository.deleteById(dentalSupplies_AppointmentId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteDentalSuppliesAppointment(Integer dentalSupplies_AppointmentId) {
        try {
            var dentalSuppliesAppointment=dentalSuppliesAppointmentRepository.findById(dentalSupplies_AppointmentId).orElseThrow(null);
            dentalSuppliesAppointment.setDeleted(true);
            dentalSuppliesAppointmentRepository.save(dentalSuppliesAppointment);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
