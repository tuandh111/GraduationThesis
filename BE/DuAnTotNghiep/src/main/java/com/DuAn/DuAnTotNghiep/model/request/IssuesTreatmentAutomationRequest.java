package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssuesTreatmentAutomationRequest {
    @NotNull
    private int issuesId;

    private int treatmentId;
    @NotNull
    private String description;
}
