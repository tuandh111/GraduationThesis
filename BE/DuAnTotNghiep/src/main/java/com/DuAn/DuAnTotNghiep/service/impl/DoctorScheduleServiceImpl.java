package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.DoctorScheduleRepository;
import com.DuAn.DuAnTotNghiep.service.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
}
