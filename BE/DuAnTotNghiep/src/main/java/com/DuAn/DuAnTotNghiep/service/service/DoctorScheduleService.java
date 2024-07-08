package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.Date;
import java.util.List;

public interface DoctorScheduleService {
    DoctorSchedule findByDoctorScheduleId(int doctorScheduleId);

    List<DoctorSchedule> findAllDoctorScheduleByDoctor(int doctorId);
    List<DoctorSchedule> findAllDoctorScheduleByDate(Date date);

    List<Object> findDoctorFromDoctorSchedule();

    List<Object> findShiftOfDoctorFromDoctorSchedule(Date date,int doctorId);

    List<Object> findDoctorScheduleByTimeRange(Date startDate, Date endDate);

    List<DoctorSchedule> findAllDoctorSchedule();

    List<DoctorSchedule> findAllDoctorScheduleExceptDeleted();

    DoctorSchedule saveDoctorSchedule(DoctorScheduleRequest doctorscheduleRequest);

    DoctorSchedule updateDoctorSchedule(int DoctorScheduleId, DoctorScheduleRequest doctorScheduleRequest);

    MessageResponse delete(int doctorScheduleId);

    MessageResponse softDeleteDoctorSchedule(int doctorScheduleId);
}
