package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DentalStaff;
import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.DentalStaffRequest;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DentalStaffService {
    DentalStaff findByDentalStaffId(int dentalStaffId);

    List<DentalStaff> findAll();

    DentalStaff saveDentalStaff(DentalStaffRequest dentalStaffRequest);

    DentalStaff updateDentalStaff(int dentalStaffId, DentalStaffRequest dentalStaffRequest);

    MessageResponse delete(int dentalStaffId);

    MessageResponse sortDeleteDentalStaff(int dentalStaffId);
}
