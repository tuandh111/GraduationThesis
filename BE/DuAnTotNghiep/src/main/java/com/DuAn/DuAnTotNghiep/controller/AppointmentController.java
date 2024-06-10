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

    @GetMapping("list-appointment")
    @Operation(summary = "List appointment")
    public ResponseEntity<List<Appointment>> getAllAbnormality() {
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

    @GetMapping("appointment-id/{Id}")
    @Operation(summary = "dental appointment Id")
    public ResponseEntity<Appointment> getAbnormalityId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentService.findByAppointmentId(Id));
    }
    @PostMapping("save-appointment")
    @Operation(summary = "Save appointment")
    public ResponseEntity<Appointment> saveAbnormality(@Valid @RequestBody AppointmentRequest appointmentRequest){
        return ResponseEntity.ok(appointmentService.saveAppointment(appointmentRequest));
    }
    @PutMapping("appointment/{Id}")
    @Operation(summary = "update appointment")
    public ResponseEntity<Appointment> updateAbnormality(@PathVariable int Id, @Valid @RequestBody AppointmentRequest appointmentRequest){
        return ResponseEntity.ok(appointmentService.updateAppointment(Id, appointmentRequest));
    }

    @DeleteMapping("delete-appointment/{Id}")
    @Operation(summary = "delete appointment")
    public ResponseEntity<MessageResponse> deleteAbnormality(@PathVariable int Id){
        return ResponseEntity.ok(appointmentService.delete(Id));
    }
}
