package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentService {
    Appointment findByAppointmentId(int appointmentId);

    List<Appointment> findAllAppointment();

    List<Appointment> findAllAppointmentExceptDeleted();

    Appointment saveAppointment(AppointmentRequest appointmentRequest);

    Appointment updateAppointment(int appointmentId, AppointmentRequest appointmentRequest);

    MessageResponse delete(int appointmentId);

    MessageResponse softDeleteAppointment(int appointmentId);

    List<AppointmentWithServicesResponse> findAllAppointmentService();

}
