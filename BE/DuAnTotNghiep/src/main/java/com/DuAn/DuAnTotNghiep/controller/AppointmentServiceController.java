package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentService;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentServiceRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentServiceService;
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
public class AppointmentServiceController {
    @Autowired
    AppointmentServiceService appointmentServiceService;
    @GetMapping("appointment-service")
    @Operation(summary = "List appointment service")
    public ResponseEntity<List<AppointmentService>> getAllAppointmentService() {
        return ResponseEntity.ok(appointmentServiceService.findAllAppointmentService());
    }

    @GetMapping("appointment-service-except-deleted")
    @Operation(summary = "List appointment service except deleted")
    public ResponseEntity<List<AppointmentService>> getAllAppointmentServiceExceptDeleted() {
        return ResponseEntity.ok(appointmentServiceService.findAllAppointmentServiceExceptDeleted());
    }

    @GetMapping("appointment-service-by-appid")
    @Operation(summary = "List appointment service by appointment id")
    public ResponseEntity<List<AppointmentService>> getAppointmentServiceByAppId(@RequestParam("appId") Integer appointmentId) {
        return ResponseEntity.ok(appointmentServiceService.findAppointmentServiceByAppId(appointmentId));
    }

    @GetMapping("appointment-service-id/{Id}")
    @Operation(summary = "dental appointment service Id")
    public ResponseEntity<AppointmentService> getAppointmentServiceId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentServiceService.findByAppointmentServiceId(Id));
    }
    @GetMapping("appointment-service-appointment-id/{Id}")
    @Operation(summary = "dental appointment service by appointment Id")
    public ResponseEntity<List<AppointmentService>> getAppointmentServiceByAppointmentId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentServiceService.findByAppointmentServiceByAppointmentId(Id));
    }
    @PostMapping("appointment-service")
    @Operation(summary = "save appointment service")
    public ResponseEntity<AppointmentService> saveAppointmentService(@Valid @RequestBody AppointmentServiceRequest appointmentServiceRequest){
        return ResponseEntity.ok(appointmentServiceService.saveAppointmentService(appointmentServiceRequest));
    }
    @PutMapping("appointment-service/{Id}")
    @Operation(summary = "update appointment service")
    public ResponseEntity<AppointmentService> updateAppointmentStatus(@PathVariable int Id, @Valid @RequestBody AppointmentServiceRequest appointmentServiceRequest){
        return ResponseEntity.ok(appointmentServiceService.updateAppointmentService(Id, appointmentServiceRequest));
    }

    @DeleteMapping("appointment-service/{Id}")
    @Operation(summary = "delete appointment service")
    public ResponseEntity<MessageResponse> deleteAppointmentStatus(@PathVariable int Id){
        return ResponseEntity.ok(appointmentServiceService.delete(Id));
    }

    @DeleteMapping("soft-delete-appointment-service/{Id}")
    @Operation(summary = "delete soft appointment status")
    public ResponseEntity<MessageResponse> softDeleteAppointmentStatus(@PathVariable int Id){
        return ResponseEntity.ok(appointmentServiceService.softDeleteAppointmentService(Id));
    }
}
