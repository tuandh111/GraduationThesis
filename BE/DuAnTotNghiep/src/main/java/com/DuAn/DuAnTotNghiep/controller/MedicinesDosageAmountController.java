package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageAmount;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageAmountRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageAmountService;
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
public class MedicinesDosageAmountController {

    @Autowired
    MedicinesDosageAmountService medicinesDosageAmountService ;

    @GetMapping("/medicines-dosage-amounts")
    @Operation(summary = "List medicines dosage amounts")
    public ResponseEntity<List<MedicinesDosageAmount>> getAllMedicinesDosageAmounts() {
        return ResponseEntity.ok(medicinesDosageAmountService.findAllMedicinesDosageAmounts()) ;
    }

    @GetMapping("/medicines-dosage-amounts-id/{id}")
    @Operation(summary = "Get medicine dosage amount by ID")
    public ResponseEntity<MedicinesDosageAmount> getMedicineDosageAmountById(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageAmountService.findByMedicinesDosageAmountId(id)) ;
    }

    @PostMapping("/medicines-dosage-amounts")
    @Operation(summary = "Save medicine dosage amount")
    public ResponseEntity<MedicinesDosageAmount> saveMedicineDosageAmount(@Valid @RequestBody MedicinesDosageAmountRequest medicinesDosageAmountRequest) {
        return ResponseEntity.ok(medicinesDosageAmountService.saveMedicinesDosageAmount(medicinesDosageAmountRequest)) ;
    }

    @PutMapping("/medicines-dosage-amounts/{id}")
    @Operation(summary = "Update medicine dosage amount")
    public ResponseEntity<MedicinesDosageAmount> updateMedicineDosageAmount(@PathVariable Integer id, @Valid @RequestBody MedicinesDosageAmountRequest medicinesDosageAmountRequest) {
        return ResponseEntity.ok(medicinesDosageAmountService.updateMedicinesDosageAmount(id, medicinesDosageAmountRequest)) ;
    }

    @DeleteMapping("/medicines-dosage-amounts/{id}")
    @Operation(summary = "Delete medicine dosage amount")
    public ResponseEntity<MessageResponse> deleteMedicineDosageAmount(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageAmountService.deleteMedicinesDosageAmount(id)) ;
    }

    @DeleteMapping("/soft-delete-medicines-dosage-amount/{id}")
    @Operation(summary = "Soft delete medicine dosage amount")
    public ResponseEntity<MessageResponse> softDeleteMedicineDosageAmount(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageAmountService.softDeleteMedicinesDosageAmount(id)) ;
    }
}
