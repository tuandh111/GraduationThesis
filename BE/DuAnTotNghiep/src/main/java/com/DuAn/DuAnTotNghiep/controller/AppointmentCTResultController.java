package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentCTResult;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentCTResultRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentCTResultService;
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
public class AppointmentCTResultController {
    @Autowired
    AppointmentCTResultService appointmentCTResultService;
    @GetMapping("appointment-ct-result")
    @Operation(summary = "List appointment ct result")
    public ResponseEntity<List<AppointmentCTResult>> getAllAppointmentCTResult() {
        return ResponseEntity.ok(appointmentCTResultService.findAll());
    }

    @GetMapping("appointment-ct-result-id/{Id}")
    @Operation(summary = "dental appointment ct result Id")
    public ResponseEntity<AppointmentCTResult> getAppointmentCTResultId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentCTResultService.findByAppointmentCTResultId(Id));
    }
    @PostMapping("appointment-ct-result")
    @Operation(summary = "save appointment ct result")
    public ResponseEntity<AppointmentCTResult> saveAppointmentCTResult(@Valid @RequestBody AppointmentCTResultRequest appointmentCTResultRequest){
        return ResponseEntity.ok(appointmentCTResultService.saveAppointmentCTResult(appointmentCTResultRequest));
    }
    @PutMapping("appointment-ct-result/{Id}")
    @Operation(summary = "update appointment ct result")
    public ResponseEntity<AppointmentCTResult> updateAppointmentCTResult(@PathVariable int Id, @Valid @RequestBody AppointmentCTResultRequest appointmentCTResultRequest){
        return ResponseEntity.ok(appointmentCTResultService.updateAppointmentCTResult(Id, appointmentCTResultRequest));
    }

    @DeleteMapping("appointment-ct-result/{Id}")
    @Operation(summary = "delete abnormality")
    public ResponseEntity<MessageResponse> deleteAppointmentCTResult(@PathVariable int Id){
        return ResponseEntity.ok(appointmentCTResultService.deleteById(Id));
    }

    @DeleteMapping("sort-delete-appointment-ct-result/{Id}")
    @Operation(summary = "delete sort abnormality")
    public ResponseEntity<MessageResponse> sortDeleteAppointmentCTResult(@PathVariable int Id){
        return ResponseEntity.ok(appointmentCTResultService.sortDeleteAppointmentCTResult(Id));
    }
}
