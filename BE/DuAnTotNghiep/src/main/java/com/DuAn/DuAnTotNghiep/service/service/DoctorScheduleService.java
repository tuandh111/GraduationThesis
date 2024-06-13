package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DoctorScheduleService {
    DoctorSchedule findByDoctorScheduleId(int doctorScheduleId);

    List<DoctorSchedule> findAll();

    DoctorSchedule saveDoctorSchedule(DoctorScheduleRequest doctorscheduleRequest);

    DoctorSchedule updateDoctorSchedule(int DoctorScheduleId, DoctorScheduleRequest doctorScheduleRequest);

    MessageResponse delete(int doctorScheduleId);

    MessageResponse sortDeleteDoctorSchedule(int doctorScheduleId);
}