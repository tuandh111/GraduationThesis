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
    @GetMapping("list-appointment-status")
    @Operation(summary = "List appointment")
    public ResponseEntity<List<AppointmentStatus>> getAllAppointmentStatus() {
        return ResponseEntity.ok(appointmentStatusService.findAll());
    }

    @GetMapping("appointment-status-id/{Id}")
    @Operation(summary = "dental appointment status Id")
    public ResponseEntity<AppointmentStatus> getAppointmentStatusId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentStatusService.findByAppointmentStatusId(Id));
    }
    @PostMapping("save-appointment-status")
    @Operation(summary = "save appointment status")
    public ResponseEntity<AppointmentStatus> saveAbnormality(@Valid @RequestBody AppointmentStatusRequest appointmentStatusRequest){
        return ResponseEntity.ok(appointmentStatusService.saveAppointmentStatus(appointmentStatusRequest));
    }
    @PutMapping("appointment-status/{Id}")
    @Operation(summary = "update appointment status")
    public ResponseEntity<AppointmentStatus> updateAppointmentStatus(@PathVariable int Id, @Valid @RequestBody AppointmentStatusRequest appointmentStatusRequest){
        return ResponseEntity.ok(appointmentStatusService.updateAppointmentStatus(Id, appointmentStatusRequest));
    }

    @DeleteMapping("delete-appointment-status/{Id}")
    @Operation(summary = "delete abnormality")
    public ResponseEntity<MessageResponse> deleteAppointmentStatus(@PathVariable int Id){
        return ResponseEntity.ok(appointmentStatusService.delete(Id));
    }
}
