package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.TreatmentDuration;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentDurationRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentDurationService;
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
public class TreatmentDurationController {
    @Autowired
    TreatmentDurationService treatmentDurationService;

    @GetMapping("/treatment-durations")
    @Operation(summary = "List treatment durations")
    public ResponseEntity<List<TreatmentDuration>> getAllTreatmentDurations() {
        return ResponseEntity.ok(treatmentDurationService.findAllTreatmentDurations());
    }

    @GetMapping("/treatment-durations/{id}")
    @Operation(summary = "Get treatment duration by ID")
    public ResponseEntity<TreatmentDuration> getTreatmentDurationById(@PathVariable Integer id) {
        return ResponseEntity.ok(treatmentDurationService.findByTreatmentDurationId(id));
    }

    @PostMapping("/treatment-durations")
    @Operation(summary = "Save treatment duration")
    public ResponseEntity<TreatmentDuration> saveTreatmentDuration(@Valid @RequestBody TreatmentDurationRequest treatmentDurationRequest) {
        return ResponseEntity.ok(treatmentDurationService.saveTreatmentDuration(treatmentDurationRequest));
    }

    @PutMapping("/treatment-durations/{id}")
    @Operation(summary = "Update treatment duration")
    public ResponseEntity<TreatmentDuration> updateTreatmentDuration(@PathVariable Integer id, @Valid @RequestBody TreatmentDurationRequest treatmentDurationRequest) {
        return ResponseEntity.ok(treatmentDurationService.updateTreatmentDuration(id, treatmentDurationRequest));
    }

    @DeleteMapping("/treatment-durations/{id}")
    @Operation(summary = "Delete treatment duration")
    public ResponseEntity<MessageResponse> deleteTreatmentDuration(@PathVariable Integer id) {
        return ResponseEntity.ok(treatmentDurationService.deleteTreatmentDuration(id));
    }

    @DeleteMapping("/soft-delete-treatment-duration/{id}")
    @Operation(summary = "Soft delete treatment duration")
    public ResponseEntity<MessageResponse> softDeleteTreatmentDuration(@PathVariable Integer id) {
        return ResponseEntity.ok(treatmentDurationService.softDeleteTreatmentDuration(id));
    }
}

