package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentRecordIssuesRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentRecordIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentRecordIssuesServiceImpl implements AppointmentRecordIssuesService {
    @Autowired
    AppointmentRecordIssuesRepository appointmentRecordIssuesRepository;
}
