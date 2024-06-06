package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.Service_TreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.Service_TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service_TreatmentServiceImpl implements Service_TreatmentService {
    @Autowired
    Service_TreatmentRepository serviceTreatmentRepository;
}
