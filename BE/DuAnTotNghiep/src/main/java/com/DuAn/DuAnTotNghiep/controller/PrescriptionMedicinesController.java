package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.utils.PrescriptionMedicinesService;
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
public class PrescriptionMedicinesController {
    @Autowired
    PrescriptionMedicinesService prescriptionMedicinesService ;

    @GetMapping("/prescription-medicines")
    @Operation(summary = "List prescription medicines")
    public ResponseEntity<List<PrescriptionMedicines>> getAllPrescriptionMedicines() {
        return ResponseEntity.ok(prescriptionMedicinesService.findAllPrescriptionMedicines()) ;
    }

    @GetMapping("/prescription-medicines-id/{id}")
    @Operation(summary = "Get prescription medicine by ID")
    public ResponseEntity<PrescriptionMedicines> getPrescriptionMedicinesById(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionMedicinesService.findPrescriptionMedicinesById(id)) ;
    }

    @PostMapping("/prescription-medicines")
    @Operation(summary = "Save prescription medicine")
    public ResponseEntity<PrescriptionMedicines> savePrescriptionMedicines(@Valid @RequestBody PrescriptionMedicinesRequest prescriptionMedicinesRequest) {
        return ResponseEntity.ok(prescriptionMedicinesService.savePrescriptionMedicines(prescriptionMedicinesRequest)) ;
    }

    @PutMapping("/prescription-medicines/{id}")
    @Operation(summary = "Update prescription medicine")
    public ResponseEntity<PrescriptionMedicines> updatePrescriptionMedicines(@PathVariable Integer id, @Valid @RequestBody PrescriptionMedicinesRequest prescriptionMedicinesRequest) {
        return ResponseEntity.ok(prescriptionMedicinesService.updatePrescriptionMedicines(id, prescriptionMedicinesRequest)) ;
    }

    @DeleteMapping("/prescription-medicines/{id}")
    @Operation(summary = "Delete prescription medicine")
    public ResponseEntity<MessageResponse> deletePrescriptionMedicines(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionMedicinesService.deletePrescriptionMedicines(id)) ;
    }

    @DeleteMapping("/soft-delete-prescription-medicines/{id}")
    @Operation(summary = "Soft delete prescription medicine")
    public ResponseEntity<MessageResponse> softDeletePrescriptionMedicines(@PathVariable Integer id) {
        return ResponseEntity.ok(prescriptionMedicinesService.softDeletePrescriptionMedicines(id)) ;
    }
}

