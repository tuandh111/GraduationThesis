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

    @GetMapping("list-dental-supplies")
    @Operation(summary = "List dentail supplies")
    public ResponseEntity<List<DentalSupplies>> getAllDentalSupplies() {
        return ResponseEntity.ok(dentalSuppliesService.findAll());
    }

    @GetMapping("dentail-supplies-id/{Id}")
    @Operation(summary = "dental supplies Id")
    public ResponseEntity<DentalSupplies> getDentalSuppliesId( @PathVariable Integer Id) {
        return ResponseEntity.ok(dentalSuppliesService.findByDentalSuppliesId(Id));
    }
    @PostMapping("save-dental-supplies")
    @Operation(summary = "Save dental supplies")
    public ResponseEntity<DentalSupplies> saveDentalSupplies(@Valid @RequestBody DentalSuppliesRequest dentalSuppliesRequest){
        return ResponseEntity.ok(dentalSuppliesService.saveDentalSupplies(dentalSuppliesRequest));
    }
    @PutMapping("update-dental-supplies/{Id}")
    @Operation(summary = "update dental supplies")
    public ResponseEntity<DentalSupplies> updateDentalSupplies(@PathVariable int Id, @Valid @RequestBody DentalSuppliesRequest dentalSuppliesRequest){
        return ResponseEntity.ok(dentalSuppliesService.updateDentalSupplies(Id, dentalSuppliesRequest));
    }

    @DeleteMapping("delete-dental-supplies/{Id}")
    @Operation(summary = "delete dentail supplies")
    public ResponseEntity<MessageResponse> deleteDentailSupplies(@PathVariable int Id){
        return ResponseEntity.ok(dentalSuppliesService.delete(Id));
    }
}
