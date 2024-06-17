package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.AppointmentPatientRecord;
import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentPatientRecordService {

    AppointmentPatientRecord findByAppointmentPatientRecordId(int appointmentPatientRecordId);

    List<AppointmentPatientRecord> findAllAppointmentPatientRecord();

    List<AppointmentPatientRecord> findAllAppointmentPatientRecordExceptDeleted();

    AppointmentPatientRecord saveAppointmentPatientRecord(AppointmentPatientRecordRequest AppointmentPatientRecordRequest);

    AppointmentPatientRecord updateAppointmentPatientRecord(int appointmentTypeId, AppointmentPatientRecordRequest AppointmentPatientRecordRequest);

    MessageResponse delete(int appointmentPatientRecordId);

    MessageResponse sortDeleteAppointmentType(int appointmentPatientRecordId);
}
