package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentService {
    Appointment findByAppointmentId(int appointmentId);

    List<Appointment> findAllAppointment();

    List<Appointment> findAllAppointmentExceptDeleted();

    Appointment saveAppointment(AppointmentRequest appointmentRequest);

    Appointment updateAppointment(int appointmentId, AppointmentRequest appointmentRequest);

    MessageResponse delete(int appointmentId);

    MessageResponse softDeleteAppointment(int appointmentId);


    List<AppointmentWithServicesResponse> findAllAppointmentService();

    List<AppointmentWithServicesResponse> findAllBillCancel();

    AppointmentWithServicesResponse findAppointmentServiceByAppointmentId(int appointmentId);

    List<AppointmentWithServicesResponse> findAllAppointmentService(String startDate, String endDate);


    List<Object> findAllDateOfAppointment();

    List<Appointment> findAppointmentByDate(Date date);

    List<Appointment> findAllAppByTimeRange(Date startDate, Date endDate);

    Map<Date, List<Appointment>> findAllAppGroupByDate(Date startDate, Date endDate, List<Integer> patientIds, List<Integer> doctorIds);


}
