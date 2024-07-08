package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentService;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentServiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.InvoiceRes;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentServiceService {
    AppointmentService findByAppointmentServiceId(int appointmentId);

    List<AppointmentService> findByAppointmentServiceByAppointmentId(int appointmentId);

    List<AppointmentService> findAllAppointmentService();

    List<AppointmentService> findAllAppointmentServiceExceptDeleted();

    AppointmentService saveAppointmentService(AppointmentServiceRequest appointmentServiceRequest);

    AppointmentService updateAppointmentService(int appointmentServiceId, AppointmentServiceRequest appointmentServiceRequest);

    MessageResponse delete(int appointmentServiceId);

    MessageResponse softDeleteAppointmentService(int appointmentServiceId);
}
