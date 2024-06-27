package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DentalStaff;
import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.DentalStaffRequest;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DentalStaffService;
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
public class DentalStaffController {
    @Autowired
    DentalStaffService dentalStaffService;
    @GetMapping("dental-staff")
    @Operation(summary = "List dental staff")
    public ResponseEntity<List<DentalStaff>> getAllDentalStaff() {
        return ResponseEntity.ok(dentalStaffService.findAllDentalStaff());
    }

    @GetMapping("dental-staff-except-deleted")
    @Operation(summary = "List dental staff except deleted")
    public ResponseEntity<List<DentalStaff>> getAllDentalStaffExceptDeleted() {
        return ResponseEntity.ok(dentalStaffService.findAllDentalStaffExceptDeleted());
    }

    @GetMapping("dental-staff-id/{Id}")
    @Operation(summary = "dental dental staff Id")
    public ResponseEntity<DentalStaff> getDentalStaffId( @PathVariable Integer Id) {
        return ResponseEntity.ok(dentalStaffService.findByDentalStaffId(Id));
    }
    @PostMapping("dental-staff")
    @Operation(summary = "Save Department")
    public ResponseEntity<DentalStaff> saveDepartment(@Valid @RequestBody DentalStaffRequest dentalStaffRequest){
        return ResponseEntity.ok(dentalStaffService.saveDentalStaff(dentalStaffRequest));
    }
    @PutMapping("dental-staff/{Id}")
    @Operation(summary = "update Department")
    public ResponseEntity<DentalStaff> updateDepartment(@PathVariable int Id, @Valid @RequestBody DentalStaffRequest dentalStaffRequest){
        return ResponseEntity.ok(dentalStaffService.updateDentalStaff(Id, dentalStaffRequest));
    }

    @DeleteMapping("dental-staff/{Id}")
    @Operation(summary = "delete dental staff")
    public ResponseEntity<MessageResponse> deleteDentalStaff(@PathVariable int Id){
        return ResponseEntity.ok(dentalStaffService.delete(Id));
    }

    @DeleteMapping("soft-delete-dental-staff/{Id}")
    @Operation(summary = "delete soft dental staff")
    public ResponseEntity<MessageResponse> softDeleteDentalStaff(@PathVariable int Id){
        return ResponseEntity.ok(dentalStaffService.softDeleteDentalStaff(Id));
    }
}
