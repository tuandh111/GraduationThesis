package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.PatientService;
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
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping("patient")
    @Operation(summary = "List patient")
    public ResponseEntity<List<Patient>> getAllPatient() {
        return ResponseEntity.ok(patientService.findAllPatient());
    }

    @GetMapping("patient-except-deleted")
    @Operation(summary = "List patient except deleted")
    public ResponseEntity<List<Patient>> getAllPatientExceptDeleted() {
        return ResponseEntity.ok(patientService.findAllPatientExceptDeleted());
    }

    @GetMapping("patient-id/{Id}")
    @Operation(summary = "patientId")
    public ResponseEntity<Patient> getPatient( @PathVariable Integer Id) {
        return ResponseEntity.ok(patientService.findByPatientId(Id));
    }
    @PostMapping("patient")
    @Operation(summary = "Save patient")
    public ResponseEntity<Patient> savePatient(@Valid @RequestBody PatientRequest patientRequest){
        return ResponseEntity.ok(patientService.savePatient(patientRequest));
    }
    @PutMapping("patient/{Id}")
    @Operation(summary = "update patient")
    public ResponseEntity<Patient> updatePatient(@PathVariable int Id, @Valid @RequestBody PatientRequest patientRequest){
        return ResponseEntity.ok(patientService.updatePatient(Id, patientRequest));
    }

    @DeleteMapping("patient/{Id}")
    @Operation(summary = "delete patient")
    public ResponseEntity<MessageResponse> deletePatient(@PathVariable int Id){
        return ResponseEntity.ok(patientService.delete(Id));
    }

    @DeleteMapping("sort-delete-patient/{Id}")
    @Operation(summary = "delete sort patient")
    public ResponseEntity<MessageResponse> sortDeletePatient(@PathVariable int Id){
        return ResponseEntity.ok(patientService.sortDeletePatient(Id));
    }
}
