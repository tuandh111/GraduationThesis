package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
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
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("appointment")
    @Operation(summary = "List appointment")
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

    @GetMapping("appointment-id/{Id}")
    @Operation(summary = "dental appointment Id")
    public ResponseEntity<Appointment> getAppointmentId(@PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentService.findByAppointmentId(Id));
    }

    @PostMapping("appointment")
    @Operation(summary = "Save appointment")
    public ResponseEntity<Appointment> saveAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointmentRequest));
    }

    @PutMapping("appointment/{Id}")
    @Operation(summary = "update appointment")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int Id, @Valid @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.updateAppointment(Id, appointmentRequest));
    }

    @DeleteMapping("appointment/{Id}")
    @Operation(summary = "delete appointment")
    public ResponseEntity<MessageResponse> deleteAppointmentId(@PathVariable int Id) {
        return ResponseEntity.ok(appointmentService.delete(Id));
    }

    @DeleteMapping("sort-delete-appointment/{Id}")
    @Operation(summary = "delete sort appointment")
    public ResponseEntity<MessageResponse> sortDeleteAppointmentId(@PathVariable int Id) {
        return ResponseEntity.ok(appointmentService.sortDeleteAppointment(Id));
    }
}