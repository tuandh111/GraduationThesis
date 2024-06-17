package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DentalSuppliesService;
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
public class DentalSuppliesController {
    @Autowired
    DentalSuppliesService dentalSuppliesService;

    @GetMapping("dental-supplies")
    @Operation(summary = "List dentail supplies")
    public ResponseEntity<List<DentalSupplies>> getAllDentalSupplies() {
        return ResponseEntity.ok(dentalSuppliesService.findAllDentalSupplies());
    }

    @GetMapping("dental-supplies-except-deleted")
    @Operation(summary = "List dentail supplies except deleted")
    public ResponseEntity<List<DentalSupplies>> getAllDentalSuppliesExceptDeleted() {
        return ResponseEntity.ok(dentalSuppliesService.findAllDentalSuppliesExceptDeleted());
    }

    @GetMapping("dental-supplies-id/{Id}")
    @Operation(summary = "dental supplies Id")
    public ResponseEntity<DentalSupplies> getDentalSuppliesId( @PathVariable Integer Id) {
        return ResponseEntity.ok(dentalSuppliesService.findByDentalSuppliesId(Id));
    }
    @PostMapping("dental-supplies")
    @Operation(summary = "Save dental supplies")
    public ResponseEntity<DentalSupplies> saveDentalSupplies(@Valid @RequestBody DentalSuppliesRequest dentalSuppliesRequest){
        return ResponseEntity.ok(dentalSuppliesService.saveDentalSupplies(dentalSuppliesRequest));
    }
    @PutMapping("dental-supplies/{Id}")
    @Operation(summary = "update dental supplies")
    public ResponseEntity<DentalSupplies> updateDentalSupplies(@PathVariable int Id, @Valid @RequestBody DentalSuppliesRequest dentalSuppliesRequest){
        return ResponseEntity.ok(dentalSuppliesService.updateDentalSupplies(Id, dentalSuppliesRequest));
    }

    @DeleteMapping("dental-supplies/{Id}")
    @Operation(summary = "delete dental supplies")
    public ResponseEntity<MessageResponse> deleteDentalSupplies(@PathVariable int Id){
        return ResponseEntity.ok(dentalSuppliesService.delete(Id));
    }

    @DeleteMapping("sort-delete-dental-supplies/{Id}")
    @Operation(summary = "delete sort dental supplies")
    public ResponseEntity<MessageResponse> sortDeleteDentalSupplies(@PathVariable int Id){
        return ResponseEntity.ok(dentalSuppliesService.sortDeleteDentalSupplies(Id));
    }
}
