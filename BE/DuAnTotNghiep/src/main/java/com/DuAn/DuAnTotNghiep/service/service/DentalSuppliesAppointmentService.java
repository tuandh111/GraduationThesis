package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.entities.DentalSuppliesAppointment;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesAppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DentalSuppliesAppointmentService {

    DentalSuppliesAppointment findDentalSuppliesAppointmentById(Integer dentalSupplies_AppointmentId);

    List<DentalSuppliesAppointment> findAllDentalSuppliesAppointmentExceptDeleted();

    List<DentalSuppliesAppointment> findDentalSuppliesByAppointment(Integer appoinmentId);

    DentalSuppliesAppointment saveReq(DentalSuppliesAppointmentRequest dentalSuppliesAppointmentRequest);

    DentalSuppliesAppointment updateReq(Integer dentalSupplies_AppointmentId,DentalSuppliesAppointmentRequest dentalSuppliesAppointmentRequest);

    MessageResponse deleteReq(Integer dentalSupplies_AppointmentId);

    MessageResponse softDeleteDentalSuppliesAppointment(Integer dentalSupplies_AppointmentId);
}
