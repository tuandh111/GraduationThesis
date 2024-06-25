package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.ServiceTreatment;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.ServiceTreatmentService;
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
public class ServiceTreatmentController {
    @Autowired
    ServiceTreatmentService servicetreatmentService;
    @GetMapping("service-treatment")
    @Operation(summary = "List service treatment")
    public ResponseEntity<List<ServiceTreatment>> getAllServiceTreatment() {
        return ResponseEntity.ok(servicetreatmentService.findAllServiceTreatment());
    }

    @GetMapping("service-treatment-except-deleted")
    @Operation(summary = "List service treatment except deleted")
    public ResponseEntity<List<ServiceTreatment>> getAllServiceTreatmentExceptDeleted() {
        return ResponseEntity.ok(servicetreatmentService.findAllServiceTreatmentExceptDeleted());
    }

    @GetMapping("service-treatment-id/{Id}")
    @Operation(summary = "service treatment")
    public ResponseEntity<ServiceTreatment> getServiceTreatmentId( @PathVariable Integer Id) {
        return ResponseEntity.ok(servicetreatmentService.findByServiceTreatment(Id));
    }
    @PostMapping("service-treatment")
    @Operation(summary = "Save service treatment")
    public ResponseEntity<ServiceTreatment> saveServiceTreatment(@Valid @RequestBody ServiceTreatmentRequest serviceTreatmentRequest){
        return ResponseEntity.ok(servicetreatmentService.saveServiceTreatment(serviceTreatmentRequest));
    }
    @PutMapping("service-treatment/{Id}")
    @Operation(summary = "update service treatment")
    public ResponseEntity<ServiceTreatment> updateService(@PathVariable int Id, @Valid @RequestBody ServiceTreatmentRequest serviceTreatmentRequest){
        return ResponseEntity.ok(servicetreatmentService.updateServiceTreatment(Id, serviceTreatmentRequest));
    }

    @DeleteMapping("service-treatment/{Id}")
    @Operation(summary = "delete service treatment")
    public ResponseEntity<MessageResponse> deleteServiceTreatment(@PathVariable int Id){
        return ResponseEntity.ok(servicetreatmentService.delete(Id));
    }

    @DeleteMapping("soft-delete-service-treatment/{Id}")
    @Operation(summary = "delete soft service treatment")
    public ResponseEntity<MessageResponse> softDeleteServiceTreatment(@PathVariable int Id){
        return ResponseEntity.ok(servicetreatmentService.softDeleteServiceTreatment(Id));
    }
}
