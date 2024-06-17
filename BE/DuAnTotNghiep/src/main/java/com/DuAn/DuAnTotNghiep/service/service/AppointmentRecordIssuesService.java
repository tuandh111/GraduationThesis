package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.AppointmentRecordIssues;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRecordIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentRecordIssuesService {
    AppointmentRecordIssues findByAppointmentRecordIssuesId(int appointmentRecordIssuesId);

    List<AppointmentRecordIssues> findAllAppointmentRecordIssues();

    List<AppointmentRecordIssues> findAllAppointmentRecordIssuesExceptDeleted();

    AppointmentRecordIssues saveAppointmentRecordIssues(AppointmentRecordIssuesRequest appointmentRecordIssuesRequest);

    AppointmentRecordIssues updateAppointmentRecordIssues(int appointmentRecordIssuesId, AppointmentRecordIssuesRequest appointmentRecordIssuesRequest);

    MessageResponse delete(int appointmentRecordIssuesId);

    MessageResponse sortDeleteAppointmentRecordIssues(int appointmentRecordIssuesId);
}
