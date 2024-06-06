package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentPatientRecordRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentPatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentPatientRecordServiceImpl implements AppointmentPatientRecordService {
    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;
}
