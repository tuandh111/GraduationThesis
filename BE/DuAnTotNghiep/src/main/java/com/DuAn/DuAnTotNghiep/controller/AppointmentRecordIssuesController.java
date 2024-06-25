package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentRecordIssues;
import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRecordIssuesRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentStatusRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentRecordIssuesService;
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
public class AppointmentRecordIssuesController {
    @Autowired
    AppointmentRecordIssuesService appointmentRecordIssuesService;
    @GetMapping("appointment-record-issues")
    @Operation(summary = "List appointment record issues")
    public ResponseEntity<List<AppointmentRecordIssues>> getAllAppointmentStatus() {
        return ResponseEntity.ok(appointmentRecordIssuesService.findAllAppointmentRecordIssues());
    }

    @GetMapping("appointment-record-issues-except-deleted")
    @Operation(summary = "List appointment record issues except deleted")
    public ResponseEntity<List<AppointmentRecordIssues>> getAllAppointmentStatusExceptDeleted() {
        return ResponseEntity.ok(appointmentRecordIssuesService.findAllAppointmentRecordIssuesExceptDeleted());
    }

    @GetMapping("appointment-record-issues-id/{Id}")
    @Operation(summary = "dental appointment record issues Id")
    public ResponseEntity<AppointmentRecordIssues> getAppointmentRecordIssuesId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentRecordIssuesService.findByAppointmentRecordIssuesId(Id));
    }
    @PostMapping("appointment-record-issues")
    @Operation(summary = "save appointment record issues")
    public ResponseEntity<AppointmentRecordIssues> saveAppointmentRecordIssues(@Valid @RequestBody AppointmentRecordIssuesRequest appointmentRecordIssuesRequest){
        return ResponseEntity.ok(appointmentRecordIssuesService.saveAppointmentRecordIssues(appointmentRecordIssuesRequest));
    }
    @PutMapping("appointment-record-issues/{Id}")
    @Operation(summary = "update appointment record issues")
    public ResponseEntity<AppointmentRecordIssues> updateAppointmentRecordIssues(@PathVariable int Id, @Valid @RequestBody AppointmentRecordIssuesRequest appointmentRecordIssuesRequest){
        return ResponseEntity.ok(appointmentRecordIssuesService.updateAppointmentRecordIssues(Id, appointmentRecordIssuesRequest));
    }

    @DeleteMapping("appointment-record-issues/{Id}")
    @Operation(summary = "delete appointment record issues")
    public ResponseEntity<MessageResponse> deleteAppointmentRecordIssues(@PathVariable int Id){
        return ResponseEntity.ok(appointmentRecordIssuesService.delete(Id));
    }

    @DeleteMapping("soft-delete-appointment-record-issues/{Id}")
    @Operation(summary = "delete soft appointment record issues")
    public ResponseEntity<MessageResponse> softDeleteAppointmentRecordIssues(@PathVariable int Id){
        return ResponseEntity.ok(appointmentRecordIssuesService.softDeleteAppointmentRecordIssues(Id));
    }
}
