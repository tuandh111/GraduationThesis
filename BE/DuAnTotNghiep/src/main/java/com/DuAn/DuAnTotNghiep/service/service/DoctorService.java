package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DoctorService {
    Doctor findByDoctorId(int DoctorId);

    List<Doctor> findAllDoctor();

    List<Doctor> findAllDoctorExceptDeleted();

    Doctor saveDoctor(DoctorRequest doctorRequest);

    Doctor updateDoctor(int doctorId, DoctorRequest doctorRequest);

    MessageResponse delete(int doctorId);

    MessageResponse sortDeleteDoctor(int doctorId);
}
