package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AppointmentTypeService {
    AppointmentType findByAppointmentTypeId(int appointmentTypeId);

    List<AppointmentType> findAllAppointmentType();

    List<AppointmentType> findAllAppointmentTypeExceptDeleted();

    AppointmentType saveAppointmentType(AppointmentTypeRequest appointmentTypeRequest);

    AppointmentType updateAppointmentType(int appointmentTypeId, AppointmentTypeRequest appointmentTypeRequest);

    MessageResponse delete(int appointmentTypeId);

    MessageResponse softDeleteAppointmentType(int appointmentTypeId);
}
