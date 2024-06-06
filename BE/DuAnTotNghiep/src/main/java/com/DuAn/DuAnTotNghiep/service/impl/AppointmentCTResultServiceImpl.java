package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentCTResultRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentCTResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCTResultServiceImpl implements AppointmentCTResultService {
    @Autowired
    AppointmentCTResultRepository appointmentCTResultRepository;
}
