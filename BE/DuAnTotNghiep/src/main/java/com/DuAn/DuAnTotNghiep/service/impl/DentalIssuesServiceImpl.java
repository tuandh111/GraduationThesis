package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.DentalIssuesRepository;
import com.DuAn.DuAnTotNghiep.service.service.DentalIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentalIssuesServiceImpl implements DentalIssuesService {
    @Autowired
    DentalIssuesRepository dentalIssuesRepository;
}
