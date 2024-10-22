package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.AppointmentCTResult;
import com.DuAn.DuAnTotNghiep.entities.ImagingPlanes;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentCTResultRequest;
import com.DuAn.DuAnTotNghiep.model.request.ImagingPlanesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentCTResultService {
    AppointmentCTResult findByAppointmentCTResultId(int appointmentCTResultId);

    List<AppointmentCTResult> findAll();

    List<AppointmentCTResult> findAllExceptDeleted();

    AppointmentCTResult saveAppointmentCTResult(AppointmentCTResultRequest appointmentCTResultRequest);

    AppointmentCTResult updateAppointmentCTResult(int appointmentCTResultId, AppointmentCTResultRequest appointmentCTResultRequest);

    MessageResponse deleteById(int AppointmentCTResultId);

    MessageResponse softDeleteAppointmentCTResult(int AppointmentCTResultId);

    List<AppointmentCTResult> findAppointmentCTResultByAppId(Integer appId);
}
