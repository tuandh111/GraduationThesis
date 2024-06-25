package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.entities.AppointmentTreatment;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTreatmentService;
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
public class AppointmentTreatmentController {
    @Autowired
    AppointmentTreatmentService appointmentTreatmentService;
    @GetMapping("appointment-treatment")
    @Operation(summary = "List appointment treatment")
    public ResponseEntity<List<AppointmentTreatment>> getAllAppointmentTreatment() {
        return ResponseEntity.ok(appointmentTreatmentService.findAllAppointmentTreatment());
    }

    @GetMapping("appointment-treatment-except-deleted")
    @Operation(summary = "List appointment treatment except deleted")
    public ResponseEntity<List<AppointmentTreatment>> getAllAppointmentTreatmentExceptDeleted() {
        return ResponseEntity.ok(appointmentTreatmentService.findAllAppointmentTreatmentExceptDeleted());
    }

    @GetMapping("appointment-treatment-id/{Id}")
    @Operation(summary = "dental appointment treatment Id")
    public ResponseEntity<AppointmentTreatment> getAppointmentTreatmentId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentTreatmentService.findByAppointmentTreatmentId(Id));
    }
    @PostMapping("appointment-treatment")
    @Operation(summary = "save appointment treatment")
    public ResponseEntity<AppointmentTreatment> saveAppointmentTreatment(@Valid @RequestBody AppointmentTreatmentRequest appointmentTreatmentRequest){
        return ResponseEntity.ok(appointmentTreatmentService.saveAppointmentTreatment(appointmentTreatmentRequest));
    }
    @PutMapping("appointment-treatment/{Id}")
    @Operation(summary = "update appointment treatment")
    public ResponseEntity<AppointmentTreatment> updateAppointmentTreatment(@PathVariable int Id, @Valid @RequestBody AppointmentTreatmentRequest appointmentTreatmentRequest){
        return ResponseEntity.ok(appointmentTreatmentService.updateAppointmentTreatment(Id, appointmentTreatmentRequest));
    }

    @DeleteMapping("appointment-treatment/{Id}")
    @Operation(summary = "delete appointment treatment")
    public ResponseEntity<MessageResponse> deleteAppointmentTreatment(@PathVariable int Id){
        return ResponseEntity.ok(appointmentTreatmentService.delete(Id));
    }

    @DeleteMapping("soft-delete-appointment-treatment/{Id}")
    @Operation(summary = "delete soft appointment treatment")
    public ResponseEntity<MessageResponse> softDeleteAppointmentTreatment(@PathVariable int Id){
        return ResponseEntity.ok(appointmentTreatmentService.softDeleteAppointmentTreatment(Id));
    }
}
