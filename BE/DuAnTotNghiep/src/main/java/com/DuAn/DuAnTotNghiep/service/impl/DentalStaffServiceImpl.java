package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.DentalStaffRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentalStaffServiceImpl implements DentalStaffService {
    @Autowired
    DentalStaffRepository dentalStaffRepository;
}
