package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Department;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.DepartmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AbnormalityService;
import com.DuAn.DuAnTotNghiep.service.service.DepartmentService;
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
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("department")
    @Operation(summary = "List department")
    public ResponseEntity<List<Department>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.findAllDepartment());
    }

    @GetMapping("department-except-deleted")
    @Operation(summary = "List department except deleted")
    public ResponseEntity<List<Department>> getAllDepartmentExceptDeleted() {
        return ResponseEntity.ok(departmentService.findAllDepartmentExceptDeleted());
    }

    @GetMapping("department-id/{Id}")
    @Operation(summary = "dental Department Id")
    public ResponseEntity<Department> getDepartmentId( @PathVariable Integer Id) {
        return ResponseEntity.ok(departmentService.findByDepartmentId(Id));
    }
    @PostMapping("department")
    @Operation(summary = "Save Department")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.saveDepartment(departmentRequest));
    }
    @PutMapping("department/{Id}")
    @Operation(summary = "update Department")
    public ResponseEntity<Department> updateDepartment(@PathVariable int Id, @Valid @RequestBody DepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.updateDepartment(Id, departmentRequest));
    }

    @DeleteMapping("department/{Id}")
    @Operation(summary = "delete abnormality")
    public ResponseEntity<MessageResponse> deleteDepartment(@PathVariable int Id){
        return ResponseEntity.ok(departmentService.delete(Id));
    }

    @DeleteMapping("sort-delete-department/{Id}")
    @Operation(summary = "delete sort abnormality")
    public ResponseEntity<MessageResponse> sortDeleteDepartment(@PathVariable int Id){
        return ResponseEntity.ok(departmentService.sortDeleteDepartment(Id));
    }
}
