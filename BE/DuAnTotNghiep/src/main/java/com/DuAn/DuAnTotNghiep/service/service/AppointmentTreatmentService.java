package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.AppointmentTreatment;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentTreatmentService {

    AppointmentTreatment findByAppointmentTreatmentId(int appointmentTreatmentId);

    List<AppointmentTreatment> findAllAppointmentTreatment();

    List<AppointmentTreatment> findAllAppointmentTreatmentExceptDeleted();


    AppointmentTreatment saveAppointmentTreatment(AppointmentTreatmentRequest appointmentTreatmentRequest);

    AppointmentTreatment updateAppointmentTreatment(int appointmentTreatmentId, AppointmentTreatmentRequest appointmentTreatmentRequest);

    MessageResponse delete(int appointmentTreatmentId);

    MessageResponse sortDeleteAppointmentTreatment(int appointmentTreatmentId);
}
