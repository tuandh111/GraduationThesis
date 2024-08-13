package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DentalStaff;
import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.DuAn.DuAnTotNghiep.model.request.DentalStaffRequest;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DentalStaffRepository;
import com.DuAn.DuAnTotNghiep.repositories.DepartmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentalStaffServiceImpl implements DentalStaffService {
    @Autowired
    DentalStaffRepository dentalStaffRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DentalStaff findByDentalStaffId(int dentalStaffId) {
        return dentalStaffRepository.findById(dentalStaffId).orElseThrow(null);
    }

    @Override
    public List<DentalStaff> findAllDentalStaff() {
        return dentalStaffRepository.findAll(Sort.by(Sort.Direction.DESC,"dentalStaffId"));
    }

    @Override
    public List<DentalStaff> findAllDentalStaffExceptDeleted() {
        return dentalStaffRepository.findAll().stream()
                .filter(dentalStaff -> !dentalStaff.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public DentalStaff saveDentalStaff(DentalStaffRequest dentalStaffRequest) {
        var dentalStaff = DentalStaff
                                  .builder()
                                  .department(departmentRepository.findById(dentalStaffRequest.getDepartmentId()).orElse(null))
                                  .fullname(dentalStaffRequest.getFullName())
                                  .phoneNumber(dentalStaffRequest.getPhoneNumber())
                                  .address(dentalStaffRequest.getAddress())
                                  .birthday(dentalStaffRequest.getBirthday())
                                  .imageURL(dentalStaffRequest.getImageURL())
                                  .gender(Gender.valueOf(dentalStaffRequest.getGender()))
                                  .build();
        dentalStaffRepository.save(dentalStaff);
        return dentalStaff;
    }

    @Override
    public DentalStaff updateDentalStaff(int dentalStaffId, DentalStaffRequest dentalStaffRequest) {
        var dentalStaff = DentalStaff
                                  .builder()
                                  .dentalStaffId(dentalStaffId)
                                  .department(departmentRepository.findById(dentalStaffRequest.getDepartmentId()).orElse(null))
                                  .fullname(dentalStaffRequest.getFullName())
                                  .phoneNumber(dentalStaffRequest.getPhoneNumber())
                                  .address(dentalStaffRequest.getAddress())
                                  .birthday(dentalStaffRequest.getBirthday())
                                  .imageURL(dentalStaffRequest.getImageURL())
                                  .gender(Gender.valueOf(dentalStaffRequest.getGender()))
                                  .build();
        dentalStaffRepository.save(dentalStaff);
        return dentalStaff;
    }

    @Override
    public MessageResponse delete(int dentalStaffId) {
        try {
            dentalStaffRepository.deleteById(dentalStaffId);
            return new MessageResponse("successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteDentalStaff(int dentalStaffId) {
        try {
            var dentalStaff = dentalStaffRepository.findById(dentalStaffId)
                                      .orElseThrow(() -> new RuntimeException("dental Staff not found"));
            dentalStaff.setDeleted(true);
            dentalStaffRepository.save(dentalStaff);
            return new MessageResponse("successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
