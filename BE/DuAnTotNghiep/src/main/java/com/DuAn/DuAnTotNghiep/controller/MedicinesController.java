package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Medicines;
import com.DuAn.DuAnTotNghiep.model.request.MedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.MedicineService;
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
public class MedicinesController {

    @Autowired
    MedicineService medicineService ;

    @GetMapping("/medicines")
    @Operation(summary = "List medicines")
    public ResponseEntity<List<Medicines>> getAllMedicines() {
        List<Medicines> medicines = medicineService.findAllMedicines();
        if (medicines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/medicines-except-deleted")
    @Operation(summary = "List medicines except deleted")
    public ResponseEntity<List<Medicines>> getAllMedicinesExceptDeleted() {
        List<Medicines> medicines = medicineService.findAllMedicinesExceptDeleted();
        if (medicines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/medicines-id/{id}")
    @Operation(summary = "Get medicine by ID")
    public ResponseEntity<Medicines> getMedicineById(@PathVariable Integer id) {
        Medicines medicine = medicineService.findByMedicineId(id);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicine);
    }

    @PostMapping("/medicines")
    @Operation(summary = "Save medicine")
    public ResponseEntity<Medicines> saveMedicine(@Valid @RequestBody MedicineRequest medicineRequest) {
        return ResponseEntity.ok(medicineService.saveMedicine(medicineRequest)) ;
    }

    @PutMapping("/medicines/{id}")
    @Operation(summary = "Update medicine")
    public ResponseEntity<Medicines> updateMedicine(@PathVariable Integer id, @Valid @RequestBody MedicineRequest medicineRequest) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, medicineRequest)) ;
    }

    @DeleteMapping("/medicines/{id}")
    @Operation(summary = "Delete medicine")
    public ResponseEntity<MessageResponse> deleteMedicine(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineService.deleteMedicine(id)) ;
    }

    @DeleteMapping("/soft-delete-medicine/{id}")
    @Operation(summary = "Soft delete medicine")
    public ResponseEntity<MessageResponse> softDeleteMedicine(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineService.softDeleteMedicine(id)) ;
    }
}
