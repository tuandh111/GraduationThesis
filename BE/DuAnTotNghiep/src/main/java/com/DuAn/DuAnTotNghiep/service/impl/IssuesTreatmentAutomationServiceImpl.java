package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.IssuesTreatmentAutomationRepository;
import com.DuAn.DuAnTotNghiep.service.service.IssuesTreatmentAutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuesTreatmentAutomationServiceImpl implements IssuesTreatmentAutomationService {
    @Autowired
    IssuesTreatmentAutomationRepository issuesTreatmentAutomationRepository;
}
