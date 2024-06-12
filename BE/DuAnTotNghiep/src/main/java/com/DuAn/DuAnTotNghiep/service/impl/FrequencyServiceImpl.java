package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.FrequencyRepository;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrequencyServiceImpl implements FrequencyService {
    @Autowired
    FrequencyRepository frequencyRepository;
}
