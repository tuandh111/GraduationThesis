package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.MedicalHistoryDetailRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryDetailServiceImpl implements MedicalHistoryDetailService {
    @Autowired
    MedicalHistoryDetailRepository medicalHistoryDetailRepository;
}
