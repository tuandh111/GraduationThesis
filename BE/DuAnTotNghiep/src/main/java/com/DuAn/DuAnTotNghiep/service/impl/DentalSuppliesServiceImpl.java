package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.DentalSuppliesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentalSuppliesServiceImpl implements DentalSuppliesService {
    @Autowired
    DentalSuppliesRepository dentalSuppliesRepository;
}
