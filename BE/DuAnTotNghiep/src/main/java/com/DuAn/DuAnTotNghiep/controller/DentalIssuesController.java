package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.entities.DentalIssues;
import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.DentalIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DentalIssuesService;
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
public class DentalIssuesController {
    @Autowired
    DentalIssuesService dentalIssuesService;
    @GetMapping("dental-issues")
    @Operation(summary = "List dental issues")
    public ResponseEntity<List<DentalIssues>> getAllDentalIssues() {
        return ResponseEntity.ok(dentalIssuesService.findAllDentalIssues());
    }

    @GetMapping("dental-issues-except-deleted")
    @Operation(summary = "List dental issues except deleted")
    public ResponseEntity<List<DentalIssues>> getAllDentalIssuesExceptDeleted() {
        return ResponseEntity.ok(dentalIssuesService.findAllDentalIssuesExceptDeleted());
    }

    @GetMapping("dental-issues-id/{Id}")
    @Operation(summary = "dental issues Id")
    public ResponseEntity<DentalIssues> getDentalIssuesId( @PathVariable Integer Id) {
        return ResponseEntity.ok(dentalIssuesService.findByDentalIssuesId(Id));
    }
    @PostMapping("dental-issues")
    @Operation(summary = "Save dental issues")
    public ResponseEntity<DentalIssues> saveDentalIssues(@Valid @RequestBody DentalIssuesRequest dentalIssuesRequest){
        return ResponseEntity.ok(dentalIssuesService.saveDentalIssues(dentalIssuesRequest));
    }
    @PutMapping("dental-issues/{Id}")
    @Operation(summary = "update dental issues")
    public ResponseEntity<DentalIssues> updateDentalIssues(@PathVariable int Id, @Valid @RequestBody DentalIssuesRequest dentalIssuesRequest){
        return ResponseEntity.ok(dentalIssuesService.updateDentalIssues(Id, dentalIssuesRequest));
    }

    @DeleteMapping("dental-issues/{Id}")
    @Operation(summary = "delete dental issues")
    public ResponseEntity<MessageResponse> deleteDentalIssues(@PathVariable int Id){
        return ResponseEntity.ok(dentalIssuesService.delete(Id));
    }

    @DeleteMapping("sort-delete-dental-issues/{Id}")
    @Operation(summary = "delete sort dental issues")
    public ResponseEntity<MessageResponse> sortDeleteDentalIssues(@PathVariable int Id){
        return ResponseEntity.ok(dentalIssuesService.sortDeleteDentalIssues(Id));
    }
}
