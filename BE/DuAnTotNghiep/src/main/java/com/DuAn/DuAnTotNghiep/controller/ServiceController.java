package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Service;
import com.DuAn.DuAnTotNghiep.model.request.ServiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.ServiceService;
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
public class ServiceController {

    @Autowired
    ServiceService serviceService;
    @GetMapping("service")
    @Operation(summary = "List service")
    public ResponseEntity<List<Service>> getAllService() {
        return ResponseEntity.ok(serviceService.findAllService());
    }

    @GetMapping("service-except-deleted")
    @Operation(summary = "List service except deleted")
    public ResponseEntity<List<Service>> getAllServiceExceptDeleted() {
        return ResponseEntity.ok(serviceService.findAllServiceExceptDeleted());
    }

    @GetMapping("service-id/{Id}")
    @Operation(summary = "service")
    public ResponseEntity<Service> getServiceId( @PathVariable Integer Id) {
        return ResponseEntity.ok(serviceService.findByServiceId(Id));
    }
    @PostMapping("service")
    @Operation(summary = "Save service")
    public ResponseEntity<Service> saveService(@Valid @RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok(serviceService.saveService(serviceRequest));
    }
    @PutMapping("service/{Id}")
    @Operation(summary = "update service")
    public ResponseEntity<Service> updateService(@PathVariable int Id, @Valid @RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok(serviceService.updateService(Id, serviceRequest));
    }

    @DeleteMapping("service/{Id}")
    @Operation(summary = "delete service ")
    public ResponseEntity<MessageResponse> deleteService(@PathVariable int Id){
        return ResponseEntity.ok(serviceService.delete(Id));
    }

    @DeleteMapping("sort-delete-service/{Id}")
    @Operation(summary = "delete sort service ")
    public ResponseEntity<MessageResponse> sortDeleteService(@PathVariable int Id){
        return ResponseEntity.ok(serviceService.sortDeleteService(Id));
    }
}
