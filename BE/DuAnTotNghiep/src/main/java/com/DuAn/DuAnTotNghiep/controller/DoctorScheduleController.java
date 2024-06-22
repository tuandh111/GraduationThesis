package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DoctorScheduleService;
import com.DuAn.DuAnTotNghiep.service.service.DoctorService;
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
public class DoctorScheduleController {
    @Autowired
    DoctorScheduleService doctorScheduleService;
    @GetMapping("doctor-schedule")
    @Operation(summary = "List doctor schedule")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorSchedule() {
        return ResponseEntity.ok(doctorScheduleService.findAllDoctorSchedule());
    }

    @GetMapping("doctor-schedule-except-deleted")
    @Operation(summary = "List doctor schedule except deleted")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorScheduleExceptDeleted() {
        return ResponseEntity.ok(doctorScheduleService.findAllDoctorScheduleExceptDeleted());
    }

    @GetMapping("doctor-schedule-id/{Id}")
    @Operation(summary = "doctor schedule Id")
    public ResponseEntity<DoctorSchedule> getDoctorScheduleId( @PathVariable Integer Id) {
        return ResponseEntity.ok(doctorScheduleService.findByDoctorScheduleId(Id));
    }
    @GetMapping("doctor-schedule-by-doctor-id/{Id}")
    @Operation(summary = "doctor schedule Id")
    public ResponseEntity<List<DoctorSchedule>> getDoctorScheduleByDoctor( @PathVariable Integer Id) {
        return ResponseEntity.ok(doctorScheduleService.findAllDoctorScheduleByDoctor(Id));
    }
    @PostMapping("doctor-schedule")
    @Operation(summary = "Save doctor schedule")
    public ResponseEntity<DoctorSchedule> saveDoctorSchedule(@Valid @RequestBody DoctorScheduleRequest doctorScheduleRequest){
        return ResponseEntity.ok(doctorScheduleService.saveDoctorSchedule(doctorScheduleRequest));
    }
    @PutMapping("doctor-schedule/{Id}")
    @Operation(summary = "update doctor schedule")
    public ResponseEntity<DoctorSchedule> updateDoctorSchedule(@PathVariable int Id, @Valid @RequestBody DoctorScheduleRequest doctorScheduleRequest){
        return ResponseEntity.ok(doctorScheduleService.updateDoctorSchedule(Id, doctorScheduleRequest));
    }

    @DeleteMapping("doctor-schedule/{Id}")
    @Operation(summary = "delete doctor schedule")
    public ResponseEntity<MessageResponse> deleteDoctorSchedule(@PathVariable int Id){
        return ResponseEntity.ok(doctorScheduleService.delete(Id));
    }

    @DeleteMapping("sort-delete-doctor-schedule/{Id}")
    @Operation(summary = "delete sort doctor schedule")
    public ResponseEntity<MessageResponse> sortDeleteDoctorSchedule(@PathVariable int Id){
        return ResponseEntity.ok(doctorScheduleService.sortDeleteDoctorSchedule(Id));
    }
}
