package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DepartmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServerImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public Department findByDepartmentId(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(null);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(DepartmentRequest departmentRequest) {
        var department = Department
                                 .builder()
                                 .departmentName(departmentRequest.getDepartmentName())
                                 .description(departmentRequest.getDescription())
                                 .build();
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department updateDepartment(int departmentId, DepartmentRequest departmentRequest) {
        var department = Department
                                 .builder()
                                 .departmentId(departmentId)
                                 .departmentName(departmentRequest.getDepartmentName())
                                 .description(departmentRequest.getDescription())
                                 .build();
        departmentRepository.save(department);
        return department;
    }

    @Override
    public MessageResponse delete(int departmentId) {
        try {
            departmentRepository.deleteById(departmentId);
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
        return null;
    }
}
