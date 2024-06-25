package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.ServiceType;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.ServiceTypeService;
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
public class ServiceTypeController {
    @Autowired
    ServiceTypeService serviceTypeService;
    @GetMapping("service-type")
    @Operation(summary = "List service type")
    public ResponseEntity<List<ServiceType>> getAllServiceType() {
        return ResponseEntity.ok(serviceTypeService.findAllServiceType());
    }

    @GetMapping("service-type-except-deleted")
    @Operation(summary = "List service type except deleted")
    public ResponseEntity<List<ServiceType>> getAllServiceTypeExceptDeleted() {
        return ResponseEntity.ok(serviceTypeService.findAllServiceTypeExceptDeleted());
    }

    @GetMapping("service-type-id/{Id}")
    @Operation(summary = "service type")
    public ResponseEntity<ServiceType> getServiceTypeId( @PathVariable Integer Id) {
        return ResponseEntity.ok(serviceTypeService.findByServiceTypeId(Id));
    }
    @PostMapping("service-type")
    @Operation(summary = "Save service type")
    public ResponseEntity<ServiceType> saveServiceType(@Valid @RequestBody ServiceTypeRequest serviceTypeRequest){
        return ResponseEntity.ok(serviceTypeService.saveServiceType(serviceTypeRequest));
    }
    @PutMapping("service-type/{Id}")
    @Operation(summary = "update service type")
    public ResponseEntity<ServiceType> updateServiceType(@PathVariable int Id, @Valid @RequestBody ServiceTypeRequest serviceTypeRequest){
        return ResponseEntity.ok(serviceTypeService.updateServiceType(Id, serviceTypeRequest));
    }

    @DeleteMapping("service-type/{Id}")
    @Operation(summary = "delete service type")
    public ResponseEntity<MessageResponse> deleteServiceType(@PathVariable int Id){
        return ResponseEntity.ok(serviceTypeService.delete(Id));
    }

    @DeleteMapping("soft-delete-service-type/{Id}")
    @Operation(summary = "delete soft service type")
    public ResponseEntity<MessageResponse> softDeleteServiceType(@PathVariable int Id){
        return ResponseEntity.ok(serviceTypeService.softDeleteServiceType(Id));
    }
}
