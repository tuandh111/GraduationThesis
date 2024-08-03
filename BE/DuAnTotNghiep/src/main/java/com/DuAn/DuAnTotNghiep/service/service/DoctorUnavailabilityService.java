package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import com.DuAn.DuAnTotNghiep.model.request.DoctorUnavailabilityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DoctorUnavailabilityService {

    DoctorUnavailability findDoctorUnavailabilityById(int doctorUnavailabilityId);

    List<DoctorUnavailability> findAllDoctorUnavailability();

    List<DoctorUnavailability> findAllDoctorUnavailabilityByDoctor(int doctorId);

    List<Object> findShiftOfDoctorFromDoctorUnavailability(Date date, int doctorId);

    List<DoctorUnavailability> findDoctorUnavailabilityByAppId(Integer appointmentId);

    List<DoctorUnavailability> findAllDoctorUnavailabilityExceptDeleted();

    DoctorUnavailability saveDoctorUnavailability(DoctorUnavailabilityRequest doctorUnavailabilityRequest);

    DoctorUnavailability updateDoctorUnavailability(int doctorUnavailabilityId, DoctorUnavailabilityRequest doctorUnavailabilityRequest);

    MessageResponse delete(int abnormalityId);

    MessageResponse softDeleteDoctorUnavailability(int doctorUnavailabilityId);

    List<Object> findDistinctDateOfDuByTimeRange(Date startDate,Date endDate,Integer doctorId);

    List<DoctorUnavailability> findDoctorUnavailabilityByDate(Date date);
    public Map<Date, List<DoctorUnavailability>> findDUByTimeRangeAndDateMap(Date startDate, Date endDate,Integer doctorId);
}
