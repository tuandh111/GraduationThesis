package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.MedicineCategory;
import com.DuAn.DuAnTotNghiep.model.request.MedicineCategoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesCategoryService;
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
public class MedicineCategoryController {
    @Autowired
    MedicinesCategoryService medicineCategoryService ;

    @GetMapping("/medicine-categories")
    @Operation(summary = "List medicine categories")
    public ResponseEntity<List<MedicineCategory>> getAllMedicineCategories() {
        return ResponseEntity.ok(medicineCategoryService.findAllMedicineCategories()) ;
    }

    @GetMapping("/medicine-categories-except-deleted")
    @Operation(summary = "List medicine categories except deleted")
    public ResponseEntity<List<MedicineCategory>> getAllMedicineCategoriesExceptDeleted() {
        return ResponseEntity.ok(medicineCategoryService.findAllMedicineCategoriesExceptDeleted()) ;
    }

    @GetMapping("/medicine-categories/{id}")
    @Operation(summary = "Get medicine category by ID")
    public ResponseEntity<MedicineCategory> getMedicineCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineCategoryService.findByMedicineCategoryId(id)) ;
    }

    @PostMapping("/medicine-categories")
    @Operation(summary = "Save medicine category")
    public ResponseEntity<MedicineCategory> saveMedicineCategory(@Valid @RequestBody MedicineCategoryRequest medicineCategoryRequest) {
        return ResponseEntity.ok(medicineCategoryService.saveMedicineCategory(medicineCategoryRequest)) ;
    }

    @PutMapping("/medicine-categories-id/{id}")
    @Operation(summary = "Update medicine category")
    public ResponseEntity<MedicineCategory> updateMedicineCategory(@PathVariable Integer id, @Valid @RequestBody MedicineCategoryRequest medicineCategoryRequest) {
        return ResponseEntity.ok(medicineCategoryService.updateMedicineCategory(id, medicineCategoryRequest)) ;
    }

    @DeleteMapping("/medicine-categories/{id}")
    @Operation(summary = "Delete medicine category")
    public ResponseEntity<MessageResponse> deleteMedicineCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineCategoryService.deleteMedicineCategory(id)) ;
    }

    @DeleteMapping("/soft-delete-medicine-category/{id}")
    @Operation(summary = "Soft delete medicine category")
    public ResponseEntity<MessageResponse> softDeleteMedicineCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(medicineCategoryService.softDeleteMedicineCategory(id)) ;
    }
}

