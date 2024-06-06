package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentServiceRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceServiceImpl implements AppointmentServiceService {
    @Autowired
    AppointmentServiceRepository appointmentServiceRepository;
}
