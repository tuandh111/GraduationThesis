package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DentalIssues;
import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.entities.Service;
import com.DuAn.DuAnTotNghiep.model.request.DentalIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.request.ServiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DentalIssuesService {
    DentalIssues findByDentalIssuesId(int dentalIssuesId);

    List<DentalIssues> findAllDentalIssues();

    List<DentalIssues> findAllDentalIssuesExceptDeleted();

    DentalIssues saveDentalIssues(DentalIssuesRequest dentalIssuesRequest);

    DentalIssues updateDentalIssues(int dentalIssuesId, DentalIssuesRequest dentalIssuesRequest);

    MessageResponse delete(int dentalIssuesId);

    MessageResponse softDeleteDentalIssues(int dentalIssuesId);
}
