package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.FrequencyMedicines;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyMedicineRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyMedicinesService;
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
public class FrequencyMedicinesController {
    @Autowired
    FrequencyMedicinesService frequencyMedicinesService ;

    @GetMapping("frequency-medicines")
    @Operation(summary = "List frequency medicines")
    public ResponseEntity<List<FrequencyMedicines>> getAllFrequencyMedicines() {
        return ResponseEntity.ok(frequencyMedicinesService.findAllFrequencyMedicines()) ;
    }

    @GetMapping("frequency-medicines-id/{id}")
    @Operation(summary = "Get frequency medicine by id")
    public ResponseEntity<FrequencyMedicines> getFrequencyMedicineById(@PathVariable int id) {
        return ResponseEntity.ok(frequencyMedicinesService.findByFrequencyMedicineId(id)) ;
    }

    @PostMapping("frequency-medicines")
    @Operation(summary = "Save frequency medicine")
    public ResponseEntity<FrequencyMedicines> saveFrequencyMedicine(@Valid @RequestBody FrequencyMedicineRequest frequencyMedicineRequest) {
        return ResponseEntity.ok(frequencyMedicinesService.saveFrequencyMedicine(frequencyMedicineRequest)) ;
    }

    @PutMapping("frequency-medicines/{id}")
    @Operation(summary = "Update frequency medicine")
    public ResponseEntity<FrequencyMedicines> updateFrequencyMedicine(@PathVariable int id, @Valid @RequestBody FrequencyMedicineRequest frequencyMedicineRequest) {
        return ResponseEntity.ok(frequencyMedicinesService.updateFrequencyMedicine(id, frequencyMedicineRequest)) ;
    }

    @DeleteMapping("frequency-medicines/{id}")
    @Operation(summary = "Delete frequency medicine")
    public ResponseEntity<MessageResponse> deleteFrequencyMedicine(@PathVariable int id) {
        return ResponseEntity.ok(frequencyMedicinesService.deleteFrequencyMedicine(id)) ;
    }

    @DeleteMapping("soft-delete-frequency-medicines/{id}")
    @Operation(summary = "Soft delete frequency medicine")
    public ResponseEntity<MessageResponse> softDeleteFrequencyMedicine(@PathVariable int id) {
        return ResponseEntity.ok(frequencyMedicinesService.softDeleteFrequencyMedicine(id)) ;
    }
}
