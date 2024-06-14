package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.entities.IssuesTreatmentAutomation;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.request.IssuesTreatmentAutomationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.IssuesTreatmentAutomationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class IssuesTreatmentAutomationController {
    @Autowired
    IssuesTreatmentAutomationService issuesTreatmentAutomationService;
    @GetMapping("issues-treatment-automation")
    @Operation(summary = "List issues treatment automation")
    public ResponseEntity<List<IssuesTreatmentAutomation>> getAllIssuesTreatmentAutomation() {
        return ResponseEntity.ok(issuesTreatmentAutomationService.findAllIssuesTreatmentAutomation());
    }

    @GetMapping("issues-treatment-automation-id/{Id}")
    @Operation(summary = "issues treatment automation")
    public ResponseEntity<IssuesTreatmentAutomation> getIssuesTreatmentAutomationId( @PathVariable Integer Id) {
        return ResponseEntity.ok(issuesTreatmentAutomationService.findByIssuesTreatmentAutomationId(Id));
    }
    @PostMapping("issues-treatment-automation")
    @Operation(summary = "issues treatment automation")
    public ResponseEntity<IssuesTreatmentAutomation> saveIssuesTreatmentAutomation(@Valid @RequestBody IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest){
        return ResponseEntity.ok(issuesTreatmentAutomationService.saveIssuesTreatmentAutomation(issuesTreatmentAutomationRequest));
    }
    @PutMapping("issues-treatment-automation/{Id}")
    @Operation(summary = "update issues treatment automation")
    public ResponseEntity<IssuesTreatmentAutomation> updateIssuesTreatmentAutomation(@PathVariable int Id, @Valid @RequestBody IssuesTreatmentAutomationRequest issuesTreatmentAutomationRequest){
        return ResponseEntity.ok(issuesTreatmentAutomationService.updateIssuesTreatmentAutomation(Id, issuesTreatmentAutomationRequest));
    }

    @DeleteMapping("issues-treatment-automation/{Id}")
    @Operation(summary = "delete issues treatment automation")
    public ResponseEntity<MessageResponse> deleteIssuesTreatmentAutomation(@PathVariable int Id){
        return ResponseEntity.ok(issuesTreatmentAutomationService.delete(Id));
    }

    @DeleteMapping("sort-delete-issues-treatment-automation/{Id}")
    @Operation(summary = "delete sort issues treatment automation")
    public ResponseEntity<MessageResponse> sortDeleteIssuesTreatmentAutomation(@PathVariable int Id){
        return ResponseEntity.ok(issuesTreatmentAutomationService.sortDeleteIssuesTreatmentAutomation(Id));
    }
}
