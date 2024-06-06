package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentTreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentTreatmentServiceImpl implements AppointmentTreatmentService {
    @Autowired
    AppointmentTreatmentRepository appointmentTreatmentRepository;
}
