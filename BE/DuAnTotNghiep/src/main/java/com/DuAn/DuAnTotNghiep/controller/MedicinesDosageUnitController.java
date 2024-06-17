package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageUnit;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageUnitRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageUnitService;
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
public class MedicinesDosageUnitController {
    @Autowired
    MedicinesDosageUnitService medicinesDosageUnitService ;

    @GetMapping("/medicines-dosage-units")
    @Operation(summary = "List medicines dosage units")
    public ResponseEntity<List<MedicinesDosageUnit>> getAllMedicinesDosageUnits() {
        return ResponseEntity.ok(medicinesDosageUnitService.findAllMedicinesDosageUnits()) ;
    }

    @GetMapping("/medicines-dosage-units-except-deleted")
    @Operation(summary = "List medicines dosage units except deleted")
    public ResponseEntity<List<MedicinesDosageUnit>> getAllMedicinesDosageUnitsExceptDeleted() {
        return ResponseEntity.ok(medicinesDosageUnitService.findAllMedicinesDosageUnitsExceptDeleted()) ;
    }

    @GetMapping("/medicines-dosage-units-id/{id}")
    @Operation(summary = "Get medicine dosage unit by ID")
    public ResponseEntity<MedicinesDosageUnit> getMedicineDosageUnitById(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageUnitService.findMedicinesDosageUnitById(id)) ;
    }

    @PostMapping("/medicines-dosage-units")
    @Operation(summary = "Save medicine dosage unit")
    public ResponseEntity<MedicinesDosageUnit> saveMedicineDosageUnit(@Valid @RequestBody MedicinesDosageUnitRequest medicinesDosageUnitRequest) {
        return ResponseEntity.ok(medicinesDosageUnitService.saveMedicinesDosageUnit(medicinesDosageUnitRequest)) ;
    }

    @PutMapping("/medicines-dosage-units/{id}")
    @Operation(summary = "Update medicine dosage unit")
    public ResponseEntity<MedicinesDosageUnit> updateMedicineDosageUnit(@PathVariable Integer id, @Valid @RequestBody MedicinesDosageUnitRequest medicinesDosageUnitRequest) {
        return ResponseEntity.ok(medicinesDosageUnitService.updateMedicinesDosageUnit(id, medicinesDosageUnitRequest)) ;
    }

    @DeleteMapping("/medicines-dosage-units/{id}")
    @Operation(summary = "Delete medicine dosage unit")
    public ResponseEntity<MessageResponse> deleteMedicineDosageUnit(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageUnitService.deleteMedicinesDosageUnit(id)) ;
    }

    @DeleteMapping("/soft-delete-medicines-dosage-unit/{id}")
    @Operation(summary = "Soft delete medicine dosage unit")
    public ResponseEntity<MessageResponse> softDeleteMedicineDosageUnit(@PathVariable Integer id) {
        return ResponseEntity.ok(medicinesDosageUnitService.softDeleteMedicinesDosageUnit(id)) ;
    }
}

