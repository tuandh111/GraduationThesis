package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DistributionSupplies;
import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.model.request.DistributionSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DistributionSuppliesService;
import com.DuAn.DuAnTotNghiep.service.service.DoctorService;
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
public class DistributionSuppliesController {
    @Autowired
    DistributionSuppliesService distributionSuppliesService;
    @GetMapping("distribution-supplies")
    @Operation(summary = "List distribution supplies")
    public ResponseEntity<List<DistributionSupplies>> getAllDistributionSupplies() {
        return ResponseEntity.ok(distributionSuppliesService.findAll());
    }

    @GetMapping("distribution-supplies-id/{Id}")
    @Operation(summary = "distribution supplies Id")
    public ResponseEntity<DistributionSupplies> getDistributionSuppliesId( @PathVariable Integer Id) {
        return ResponseEntity.ok(distributionSuppliesService.findByDistributionSuppliesId(Id));
    }
    @PostMapping("distribution-supplies")
    @Operation(summary = "Save distribution supplies")
    public ResponseEntity<DistributionSupplies> saveDistributionSupplies(@Valid @RequestBody DistributionSuppliesRequest distributionSuppliesRequest){
        return ResponseEntity.ok(distributionSuppliesService.saveDistributionSupplies(distributionSuppliesRequest));
    }
    @PutMapping("distribution-supplies/{Id}")
    @Operation(summary = "update doctor")
    public ResponseEntity<DistributionSupplies> updateDistributionSupplies(@PathVariable int Id, @Valid @RequestBody DistributionSuppliesRequest distributionSuppliesRequest){
        return ResponseEntity.ok(distributionSuppliesService.updateDistributionSupplies(Id, distributionSuppliesRequest));
    }

    @DeleteMapping("distribution-supplies/{Id}")
    @Operation(summary = "delete distribution supplies")
    public ResponseEntity<MessageResponse> deleteDistributionSupplies(@PathVariable int Id){
        return ResponseEntity.ok(distributionSuppliesService.delete(Id));
    }

    @DeleteMapping("sort-delete-distribution-supplies/{Id}")
    @Operation(summary = "delete sort distribution supplies")
    public ResponseEntity<MessageResponse> sortDeleteDistributionSupplies(@PathVariable int Id){
        return ResponseEntity.ok(distributionSuppliesService.sortDeleteDistributionSupplies(Id));
    }
}
