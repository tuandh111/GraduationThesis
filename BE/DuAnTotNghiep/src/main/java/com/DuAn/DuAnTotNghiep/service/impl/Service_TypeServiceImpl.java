package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.Service_TypeRepository;
import com.DuAn.DuAnTotNghiep.service.service.Service_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service_TypeServiceImpl implements Service_TypeService {
    @Autowired
    Service_TypeRepository serviceTypeRepository;
}
