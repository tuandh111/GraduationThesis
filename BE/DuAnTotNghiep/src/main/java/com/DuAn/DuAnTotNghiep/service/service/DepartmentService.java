package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DepartmentService {
    Department findByDepartmentId(int departmentId);

    List<Department> findAll();

    Department saveDepartment(DepartmentRequest departmentRequest);

    Department updateDepartment(int departmentId, DepartmentRequest departmentRequest);

    MessageResponse delete(int departmentId);
}
