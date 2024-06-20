package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorUnavailabilityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DoctorUnavailabilityService;
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
public class DoctorUnavailabilityController {
    @Autowired
    DoctorUnavailabilityService doctorUnavailabilityService;
    @GetMapping("doctorUnavailability")
    @Operation(summary = "List doctorUnavailability")
    public ResponseEntity<List<DoctorUnavailability>> getAllDoctorUnavailability() {
        return ResponseEntity.ok(doctorUnavailabilityService.findAllDoctorUnavailability());
    }

    @GetMapping("doctorUnavailability-except-deleted")
    @Operation(summary = "List doctorUnavailability except deleted")
    public ResponseEntity<List<DoctorUnavailability>> getAllDoctorUnavailabilityExceptDeleted() {
        return ResponseEntity.ok(doctorUnavailabilityService.findAllDoctorUnavailabilityExceptDeleted());
    }

    @GetMapping("doctorUnavailability-id/{Id}")
    @Operation(summary = "dental doctorUnavailability Id")
    public ResponseEntity<DoctorUnavailability> getDoctorUnavailabilityId( @PathVariable Integer Id) {
        return ResponseEntity.ok(doctorUnavailabilityService.findDoctorUnavailabilityById(Id));
    }
    @PostMapping("doctorUnavailability")
    @Operation(summary = "Save doctorUnavailability")
    public ResponseEntity<DoctorUnavailability> saveDoctorUnavailability(@Valid @RequestBody DoctorUnavailabilityRequest doctorUnavailabilityRequest){
        return ResponseEntity.ok(doctorUnavailabilityService.saveDoctorUnavailability(doctorUnavailabilityRequest));
    }
    @PutMapping("doctorUnavailability/{Id}")
    @Operation(summary = "update doctorUnavailability")
    public ResponseEntity<DoctorUnavailability> updateDoctorUnavailability(@PathVariable int Id, @Valid @RequestBody DoctorUnavailabilityRequest doctorUnavailabilityRequest){
        return ResponseEntity.ok(doctorUnavailabilityService.updateDoctorUnavailability(Id, doctorUnavailabilityRequest));
    }

    @DeleteMapping("doctorUnavailability/{Id}")
    @Operation(summary = "delete doctorUnavailability")
    public ResponseEntity<MessageResponse> deleteDoctorUnavailability(@PathVariable int Id){
        return ResponseEntity.ok(doctorUnavailabilityService.delete(Id));
    }

    @DeleteMapping("sort-delete-doctorUnavailability/{Id}")
    @Operation(summary = "delete sort doctorUnavailability")
    public ResponseEntity<MessageResponse> sortDeleteDoctorUnavailability(@PathVariable int Id){
        return ResponseEntity.ok(doctorUnavailabilityService.softDeleteDoctorUnavailability(Id));
    }
}
