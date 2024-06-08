package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DentalIssues;
import com.DuAn.DuAnTotNghiep.entities.IssuesTreatmentAutomation;
import com.DuAn.DuAnTotNghiep.model.request.IssuesTreatmentAutomationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DentalIssuesRepository;
import com.DuAn.DuAnTotNghiep.repositories.IssuesTreatmentAutomationRepository;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.IssuesTreatmentAutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesTreatmentAutomationServiceImpl implements IssuesTreatmentAutomationService {
    @Autowired
    IssuesTreatmentAutomationRepository issuesTreatmentAutomationRepository;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    DentalIssuesRepository dentalIssuesRepository;

    @Override
    public IssuesTreatmentAutomation findByIssuesTreatmentAutomationId(int issuesTreatmentAutomationId) {
        return issuesTreatmentAutomationRepository.findById(issuesTreatmentAutomationId).orElseThrow(null);
    }

    @Override
    public List<IssuesTreatmentAutomation> findAllIssuesTreatmentAutomation() {
        return issuesTreatmentAutomationRepository.findAll();
    }

    @Override
    public IssuesTreatmentAutomation saveIssuesTreatmentAutomation(IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest) {
        var issuesTreatmentAutomation =IssuesTreatmentAutomation
                                               .builder()
                                               .dentalIssues(dentalIssuesRepository.findById(issuesTreatmentAutomationRequest.getIssuesId()).orElseThrow(null))
                                               .treatment(treatmentRepository.findById(issuesTreatmentAutomationRequest.getTreatmentId()).orElseThrow(null))
                                               .description(issuesTreatmentAutomationRequest.getDescription())
                                               .build();
        issuesTreatmentAutomationRepository.save(issuesTreatmentAutomation);
        return issuesTreatmentAutomation;
    }

    @Override
    public IssuesTreatmentAutomation updateIssuesTreatmentAutomation(int issuesTreatmentAutomationId, IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest) {
        var issuesTreatmentAutomation =IssuesTreatmentAutomation
                                               .builder()
                                               .IssuesTreatmentAutomationId(issuesTreatmentAutomationId)
                                               .dentalIssues(dentalIssuesRepository.findById(issuesTreatmentAutomationRequest.getIssuesId()).orElseThrow(null))
                                               .treatment(treatmentRepository.findById(issuesTreatmentAutomationRequest.getTreatmentId()).orElseThrow(null))
                                               .description(issuesTreatmentAutomationRequest.getDescription())
                                               .build();
        issuesTreatmentAutomationRepository.save(issuesTreatmentAutomation);
        return issuesTreatmentAutomation;
    }

    @Override
    public MessageResponse delete(int issuesTreatmentAutomationId) {
        try {
            issuesTreatmentAutomationRepository.deleteById(issuesTreatmentAutomationId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
