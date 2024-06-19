package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import com.DuAn.DuAnTotNghiep.model.request.DoctorUnavailabilityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DoctorUnavailabilityService {

    DoctorUnavailability findDoctorUnavailabilityById(int doctorUnavailabilityId);

    List<DoctorUnavailability> findAllDoctorUnavailability();

    List<DoctorUnavailability> findAllDoctorUnavailabilityExceptDeleted();

    DoctorUnavailability saveDoctorUnavailability(DoctorUnavailabilityRequest doctorUnavailabilityRequest);

    DoctorUnavailability updateDoctorUnavailability(int doctorUnavailabilityId, DoctorUnavailabilityRequest doctorUnavailabilityRequest);

    MessageResponse delete(int abnormalityId);

    MessageResponse softDeleteDoctorUnavailability(int doctorUnavailabilityId);
}
