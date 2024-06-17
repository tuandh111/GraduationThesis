package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.entities.Treatment;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.TreatmentService;
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
public class TreatmentController {
    @Autowired
    TreatmentService treatmentService;
    @GetMapping("treatment")
    @Operation(summary = "List treatment")
    public ResponseEntity<List<Treatment>> getAllTreatment() {
        return ResponseEntity.ok(treatmentService.findAllTreatment());
    }

    @GetMapping("treatment-except-deleted")
    @Operation(summary = "List treatment except deleted")
    public ResponseEntity<List<Treatment>> getAllTreatmentExceptDeleted() {
        return ResponseEntity.ok(treatmentService.findAllTreatmentExceptDeleted());
    }

    @GetMapping("treatment-id/{Id}")
    @Operation(summary = "dental treatment")
    public ResponseEntity<Treatment> getTreatmentId( @PathVariable Integer Id) {
        return ResponseEntity.ok(treatmentService.findByTreatmentId(Id));
    }
    @PostMapping("treatment")
    @Operation(summary = "Save treatment")
    public ResponseEntity<Treatment> saveTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest){
        return ResponseEntity.ok(treatmentService.saveTreatment(treatmentRequest));
    }
    @PutMapping("treatment/{Id}")
    @Operation(summary = "update treatment")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable int Id, @Valid @RequestBody TreatmentRequest treatmentRequest){
        return ResponseEntity.ok(treatmentService.updateTreatment(Id, treatmentRequest));
    }

    @DeleteMapping("treatment/{Id}")
    @Operation(summary = "delete treatment")
    public ResponseEntity<MessageResponse> deleteTreatment(@PathVariable int Id){
        return ResponseEntity.ok(treatmentService.delete(Id));
    }

    @DeleteMapping("sort-delete-treatment/{Id}")
    @Operation(summary = "delete sort treatment")
    public ResponseEntity<MessageResponse> sortDeleteTreatment(@PathVariable int Id){
        return ResponseEntity.ok(treatmentService.sortDeleteTreatment(Id));
    }
}
