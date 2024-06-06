package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.CTResultAbnormalityRepository;
import com.DuAn.DuAnTotNghiep.service.service.CTResultAbnormalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CTResultAbnormalityServiceImpl implements CTResultAbnormalityService {
    @Autowired
    CTResultAbnormalityRepository ctResultAbnormalityRepository;
}
