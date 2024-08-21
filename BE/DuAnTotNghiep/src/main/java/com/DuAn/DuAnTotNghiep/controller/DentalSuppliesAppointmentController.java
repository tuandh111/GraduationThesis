package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.entities.DentalSuppliesAppointment;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesAppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DentalSuppliesAppointmentService;
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
public class DentalSuppliesAppointmentController {
    @Autowired
    DentalSuppliesAppointmentService dentalSuppliesAppointmentService;

    @GetMapping("dental-supplies-appointment")
    @Operation(summary = "List dentail supplies appointment")
    public ResponseEntity<List<DentalSuppliesAppointment>> getAllDentalSuppliesAppointment() {
        return ResponseEntity.ok(dentalSuppliesAppointmentService.findAllDentalSuppliesAppointmentExceptDeleted());
    }

    @GetMapping("dental-supplies-appointment-by-appid/{Id}")
    @Operation(summary = "dental supplies by appointment Id")
    public ResponseEntity<List<DentalSuppliesAppointment>> getDentalSuppliesAppointmentByAppId( @PathVariable Integer Id) {
        return ResponseEntity.ok(dentalSuppliesAppointmentService.findDentalSuppliesByAppointment(Id));
    }

    @PostMapping("dental-supplies-appointment")
    @Operation(summary = "Save dental supplies appointment")
    public ResponseEntity<DentalSuppliesAppointment> saveDentalSuppliesAppointment(@Valid @RequestBody DentalSuppliesAppointmentRequest req){
        return ResponseEntity.ok(dentalSuppliesAppointmentService.saveReq(req));
    }

    @PutMapping("dental-supplies-appointment/{Id}")
    @Operation(summary = "update dental supplies appointment")
    public ResponseEntity<DentalSuppliesAppointment> updateDentalSuppliesAppointment(@PathVariable int Id, @Valid @RequestBody DentalSuppliesAppointmentRequest req){
        return ResponseEntity.ok(dentalSuppliesAppointmentService.updateReq(Id,req));
    }
    @DeleteMapping("dental-supplies-appointment/{Id}")
    @Operation(summary = "delete dental supplies appointment")
    public ResponseEntity<MessageResponse> deleteDentalSuppliesAppointment(@PathVariable int Id){
        return ResponseEntity.ok(dentalSuppliesAppointmentService.deleteReq(Id));
    }

    @DeleteMapping("soft-dental-supplies-appointment/{Id}")
    @Operation(summary = "delete soft dental supplies appointment")
    public ResponseEntity<MessageResponse> softDeleteDentalSuppliesAppointment(@PathVariable int Id){
        return ResponseEntity.ok(dentalSuppliesAppointmentService.softDeleteDentalSuppliesAppointment(Id));
    }
}
