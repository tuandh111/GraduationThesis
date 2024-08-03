package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.model.response.PrescriptionWithMedicinesResponse;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionService;
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
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService ;

    @GetMapping("/prescriptions")
    @Operation(summary = "List prescriptions")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.findAllPrescriptions()) ;
    }

    @GetMapping("/prescriptions-except-deleted")
    @Operation(summary = "List prescriptions except deleted")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsExceptDeleted() {
        return ResponseEntity.ok(prescriptionService.findAllPrescriptionsExceptDeleted()) ;
    }

    @GetMapping("/prescriptions-id/{id}")
    @Operation(summary = "Get prescription by ID")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionService.findByPrescriptionId(id)) ;
    }
    @GetMapping("/prescription-by-appointment")
    @Operation(summary = "Get prescription by appointment")
    public ResponseEntity<List<PrescriptionWithMedicinesResponse>> getPrescriptionWithMedicinesByAppointment(@RequestParam(value = "appointmentId",required = false) Integer appointmentId){
        return ResponseEntity.ok(prescriptionService.findPrescriptionByAppointment(appointmentId));
    }

    @PostMapping("/prescriptions")
    @Operation(summary = "Save prescription")
    public ResponseEntity<Prescription> savePrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest) {
        return ResponseEntity.ok(prescriptionService.savePrescription(prescriptionRequest)) ;
    }

    @PutMapping("/prescriptions/{id}")
    @Operation(summary = "Update prescription")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Integer id, @Valid @RequestBody PrescriptionRequest prescriptionRequest) {
        return ResponseEntity.ok(prescriptionService.updatePrescription(id, prescriptionRequest)) ;
    }

    @DeleteMapping("/prescriptions/{id}")
    @Operation(summary = "Delete prescription")
    public ResponseEntity<MessageResponse> deletePrescription(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionService.deletePrescription(id)) ;
    }

    @DeleteMapping("/soft-delete-prescription/{id}")
    @Operation(summary = "Soft delete prescription")
    public ResponseEntity<MessageResponse> softDeletePrescription(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionService.softDeletePrescription(id)) ;
    }
}
