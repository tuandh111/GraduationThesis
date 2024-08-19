package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.model.request.MedicalHistoryRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicalHistoryRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicalHistoryService;
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
public class MedicalHistoryController {
    @Autowired
    MedicalHistoryService medicalHistoryService;
    @GetMapping("medical-history")
    @Operation(summary = "List medical history")
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistory() {
        return ResponseEntity.ok(medicalHistoryService.findAllMedicalHistory());
    }

    @GetMapping("medical-history-except-deleted")
    @Operation(summary = "List medical history except deleted")
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistoryExceptDeleted() {
        return ResponseEntity.ok(medicalHistoryService.findAllMedicalHistoryExceptDeleted());
    }

    @GetMapping("medical-history-id/{Id}")
    @Operation(summary = "medical history Id")
    public ResponseEntity<MedicalHistory> getMedicalHistory( @PathVariable Integer Id) {
        return ResponseEntity.ok(medicalHistoryService.findByMedicalHistoryId(Id));
    }

    @GetMapping("medical-history-patientId/{Id}")
    @Operation(summary = "Get Medical history by patient")
    public ResponseEntity<List<MedicalHistory>> getMedicalHistoryByPatient(@PathVariable(required = false) Integer Id){
        return ResponseEntity.ok(medicalHistoryService.findMedicalHistoryByPatient(Id));
    }

    @PostMapping("medical-history")
    @Operation(summary = "Save Medical History")
    public ResponseEntity<MedicalHistory> saveMedicalHistory(@Valid @RequestBody MedicalHistoryRequest medicalHistoryRequest){
        return ResponseEntity.ok(medicalHistoryService.saveMedicalHistory(medicalHistoryRequest));
    }
    @PutMapping("medical-history/{Id}")
    @Operation(summary = "update medical history")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable int Id, @Valid @RequestBody MedicalHistoryRequest medicalHistoryRequest){
        return ResponseEntity.ok(medicalHistoryService.updateMedicalHistory(Id, medicalHistoryRequest));
    }

    @DeleteMapping("medical-history/{Id}")
    @Operation(summary = "delete medical history")
    public ResponseEntity<MessageResponse> deleteMedicalHistory(@PathVariable int Id){
        return ResponseEntity.ok(medicalHistoryService.delete(Id));
    }

    @DeleteMapping("soft-delete-medical-history/{Id}")
    @Operation(summary = "delete soft medical history")
    public ResponseEntity<MessageResponse> softDeleteMedicalHistory(@PathVariable int Id){
        return ResponseEntity.ok(medicalHistoryService.softDeleteMedicalHistory(Id));
    }
}
