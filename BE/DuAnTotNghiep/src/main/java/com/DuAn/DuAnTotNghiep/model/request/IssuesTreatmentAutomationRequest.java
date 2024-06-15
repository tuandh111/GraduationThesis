package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssuesTreatmentAutomationRequest {

    private int issuesId;

    private int treatmentId;

    private String description;
}
