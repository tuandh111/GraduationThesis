package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentPatientRecord;
import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentPatientRecordRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentPatientRecordService;
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
public class AppointmentPatientRecordController {
    @Autowired
    AppointmentPatientRecordService appointmentPatientRecordService;
    @GetMapping("list-appointment-patient-record")
    @Operation(summary = "List appointment patient record")
    public ResponseEntity<List<AppointmentPatientRecord>> getAllAppointmentType() {
        return ResponseEntity.ok(appointmentPatientRecordService.findAllAppointmentPatientRecord());
    }

    @GetMapping("appointment-patient-record-id/{Id}")
    @Operation(summary = "dental appointment patient record")
    public ResponseEntity<AppointmentPatientRecord> getAppointmentTypeId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentPatientRecordService.findByAppointmentPatientRecordId(Id));
    }
    @PostMapping("save-appointment-patient-record")
    @Operation(summary = "Save appointment patient record")
    public ResponseEntity<AppointmentPatientRecord> saveAppointmentType(@Valid @RequestBody AppointmentPatientRecordRequest appointmentPatientRecordRequest){
        return ResponseEntity.ok(appointmentPatientRecordService.saveAppointmentPatientRecord(appointmentPatientRecordRequest));
    }
    @PutMapping("appointment-patient-record/{Id}")
    @Operation(summary = "update appointment patient record")
    public ResponseEntity<AppointmentPatientRecord> updateAppointmentType(@PathVariable int Id, @Valid @RequestBody AppointmentPatientRecordRequest appointmentPatientRecordRequest){
        return ResponseEntity.ok(appointmentPatientRecordService.updateAppointmentPatientRecord(Id, appointmentPatientRecordRequest));
    }

    @DeleteMapping("delete-appointment-patient-record/{Id}")
    @Operation(summary = "delete appointment patient record")
    public ResponseEntity<MessageResponse> deleteAppointmentType(@PathVariable int Id){
        return ResponseEntity.ok(appointmentPatientRecordService.delete(Id));
    }
}
