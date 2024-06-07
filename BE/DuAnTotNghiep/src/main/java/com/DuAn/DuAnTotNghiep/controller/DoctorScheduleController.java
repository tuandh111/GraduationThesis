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
    @GetMapping("list-doctor-schedule")
    @Operation(summary = "List doctor schedule")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorSchedule() {
        return ResponseEntity.ok(doctorScheduleService.findAll());
    }

    @GetMapping("doctor-schedule-id/{Id}")
    @Operation(summary = "doctor schedule Id")
    public ResponseEntity<DoctorSchedule> getDoctorScheduleId( @PathVariable Integer Id) {
        return ResponseEntity.ok(doctorScheduleService.findByDoctorScheduleId(Id));
    }
    @PostMapping("save-doctor-schedule")
    @Operation(summary = "Save doctor schedule")
    public ResponseEntity<DoctorSchedule> saveDoctorSchedule(@Valid @RequestBody DoctorScheduleRequest doctorScheduleRequest){
        return ResponseEntity.ok(doctorScheduleService.saveDoctorSchedule(doctorScheduleRequest));
    }
    @PutMapping("update-doctor-schedule/{Id}")
    @Operation(summary = "update doctor schedule")
    public ResponseEntity<DoctorSchedule> updateDoctorSchedule(@PathVariable int Id, @Valid @RequestBody DoctorScheduleRequest doctorScheduleRequest){
        return ResponseEntity.ok(doctorScheduleService.updateDoctorSchedule(Id, doctorScheduleRequest));
    }

    @DeleteMapping("delete-doctor-schedule/{Id}")
    @Operation(summary = "delete doctor schedule")
    public ResponseEntity<MessageResponse> deleteDoctorSchedule(@PathVariable int Id){
        return ResponseEntity.ok(doctorScheduleService.delete(Id));
    }
}
