package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.IssuesTreatmentAutomation;
import com.DuAn.DuAnTotNghiep.model.request.DentalIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.request.IssuesTreatmentAutomationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface IssuesTreatmentAutomationService {
    IssuesTreatmentAutomation findByIssuesTreatmentAutomationId(int issuesTreatmentAutomationId);

    List<IssuesTreatmentAutomation> findAllIssuesTreatmentAutomation();

    IssuesTreatmentAutomation saveIssuesTreatmentAutomation(IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest);

    IssuesTreatmentAutomation updateIssuesTreatmentAutomation(int issuesTreatmentAutomationId, IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest);

    MessageResponse delete(int issuesTreatmentAutomationId);

    MessageResponse sortDeleteIssuesTreatmentAutomation(int issuesTreatmentAutomationId);
}
