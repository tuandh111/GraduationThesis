package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DistributionMedicines;
import com.DuAn.DuAnTotNghiep.entities.DistributionSupplies;
import com.DuAn.DuAnTotNghiep.model.request.DistributionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.request.DistributionSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DistributionMedicinesService;
import com.DuAn.DuAnTotNghiep.service.service.DistributionSuppliesService;
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
public class DistributionMedicinesController {
    @Autowired
    private DistributionMedicinesService distributionMedicinesService;

    @GetMapping("/distribution-medicines")
    @Operation(summary = "List distribution medicines")
    public ResponseEntity<List<DistributionMedicines>> getAllDistributionMedicines() {
        return ResponseEntity.ok(distributionMedicinesService.findAllDistributionMedicines());
    }

    @GetMapping("/distribution-medicines-except-deleted")
    @Operation(summary = "List distribution medicines except deleted")
    public ResponseEntity<List<DistributionMedicines>> getAllDistributionMedicinesExceptDeleted() {
        return ResponseEntity.ok(distributionMedicinesService.findAllDistributionMedicinesExceptDeleted());
    }


    @GetMapping("/distribution-medicines-id/{id}")
    @Operation(summary = "Get distribution medicine by ID")
    public ResponseEntity<DistributionMedicines> getDistributionMedicinesById(@PathVariable Integer id) {
        return ResponseEntity.ok(distributionMedicinesService.findByDistributionMedicinesId(id));
    }

    @PostMapping("/distribution-medicines")
    @Operation(summary = "Save distribution medicine")
    public ResponseEntity<DistributionMedicines> saveDistributionMedicines(@Valid @RequestBody DistributionMedicinesRequest distributionMedicinesRequest) {
        return ResponseEntity.ok(distributionMedicinesService.saveDistributionMedicines(distributionMedicinesRequest));
    }

    @PutMapping("/distribution-medicines/{id}")
    @Operation(summary = "Update distribution medicine")
    public ResponseEntity<DistributionMedicines> updateDistributionMedicines(@PathVariable Integer id, @Valid @RequestBody DistributionMedicinesRequest distributionMedicinesRequest) {
        return ResponseEntity.ok(distributionMedicinesService.updateDistributionMedicines(id, distributionMedicinesRequest));
    }

    @DeleteMapping("/distribution-medicines/{id}")
    @Operation(summary = "Delete distribution medicine")
    public ResponseEntity<MessageResponse> deleteDistributionMedicines(@PathVariable Integer id) {
        return ResponseEntity.ok(distributionMedicinesService.deleteDistributionMedicines(id));
    }

    @DeleteMapping("/soft-delete-distribution-medicines/{id}")
    @Operation(summary = "Soft delete distribution medicine")
    public ResponseEntity<MessageResponse> softDeleteDistributionMedicines(@PathVariable Integer id) {
        return ResponseEntity.ok(distributionMedicinesService.softDeleteDistributionMedicines(id));
    }
}

