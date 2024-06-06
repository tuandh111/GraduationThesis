package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentStatusRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentStatusServiceImpl implements AppointmentStatusService {
    @Autowired
    AppointmentStatusRepository appointmentStatusRepository;
}
