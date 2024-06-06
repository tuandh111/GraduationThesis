package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.AppointmentTypeRepository;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {
    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;
}
