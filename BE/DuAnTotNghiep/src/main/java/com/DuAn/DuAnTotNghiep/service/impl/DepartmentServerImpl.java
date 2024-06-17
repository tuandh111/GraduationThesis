package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DepartmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServerImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public Department findByDepartmentId(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(null);
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll() ;
    }

    @Override
    public List<Department> findAllDepartmentExceptDeleted() {
        return departmentRepository.findAll().stream()
                .filter(department -> !department.isDeleted())
                .collect(Collectors.toList());
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
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }

    }

    @Override
    public MessageResponse sortDeleteDepartment(int departmentId) {
        try {
            var department = Department
                                     .builder()
                                     .departmentId(departmentId)
                                     .isDeleted(true)
                                     .build();
            departmentRepository.save(department);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
