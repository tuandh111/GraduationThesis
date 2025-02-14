package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentStatusService;
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
public class AppointmentStatusController {
    @Autowired
    AppointmentStatusService appointmentStatusService;
    @GetMapping("appointment-status")
    @Operation(summary = "List appointment status")
    public ResponseEntity<List<AppointmentStatus>> getAllAppointmentStatus() {
        return ResponseEntity.ok(appointmentStatusService.findAllAppointmentStatus());
    }

    @GetMapping("appointment-status-except-deleted")
    @Operation(summary = "List appointment status except deleted")
    public ResponseEntity<List<AppointmentStatus>> getAllAppointmentStatusExceptDeleted() {
        return ResponseEntity.ok(appointmentStatusService.findAllAppointmentStatusExceptDeleted());
    }

    @GetMapping("appointment-status-id/{Id}")
    @Operation(summary = "dental appointment status Id")
    public ResponseEntity<AppointmentStatus> getAppointmentStatusId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentStatusService.findByAppointmentStatusId(Id));
    }
    @PostMapping("appointment-status")
    @Operation(summary = "save appointment status")
    public ResponseEntity<AppointmentStatus> saveAppointmentStatus(@Valid @RequestBody AppointmentStatusRequest appointmentStatusRequest){
        return ResponseEntity.ok(appointmentStatusService.saveAppointmentStatus(appointmentStatusRequest));
    }
    @PutMapping("appointment-status/{Id}")
    @Operation(summary = "update appointment status")
    public ResponseEntity<AppointmentStatus> updateAppointmentStatus(@PathVariable int Id, @Valid @RequestBody AppointmentStatusRequest appointmentStatusRequest){
        return ResponseEntity.ok(appointmentStatusService.updateAppointmentStatus(Id, appointmentStatusRequest));
    }

    @DeleteMapping("appointment-status/{Id}")
    @Operation(summary = "delete appointment status")
    public ResponseEntity<MessageResponse> deleteAppointmentStatus(@PathVariable int Id){
        return ResponseEntity.ok(appointmentStatusService.delete(Id));
    }

    @DeleteMapping("soft-delete-appointment-status/{Id}")
    @Operation(summary = "delete soft appointment status")
    public ResponseEntity<MessageResponse> softDeleteAppointmentStatus(@PathVariable int Id){
        return ResponseEntity.ok(appointmentStatusService.softDeleteAppointmentStatus(Id));
    }
}
