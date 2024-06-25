package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentStatusService {

    AppointmentStatus findByAppointmentStatusId(int appointmentStatusId);

    List<AppointmentStatus> findAllAppointmentStatus();

    List<AppointmentStatus> findAllAppointmentStatusExceptDeleted();

    AppointmentStatus saveAppointmentStatus(AppointmentStatusRequest appointmentStatusRequest);

    AppointmentStatus updateAppointmentStatus(int appointmentStatusId, AppointmentStatusRequest appointmentStatusRequest);

    MessageResponse delete(int appointmentStatusId);

    MessageResponse softDeleteAppointmentStatus(int appointmentStatusId);
}
