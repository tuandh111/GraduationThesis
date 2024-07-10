package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping("doctor")
    @Operation(summary = "List doctor")
    public ResponseEntity<List<Doctor>> getAllDoctor() {
        return ResponseEntity.ok(doctorService.findAllDoctor());
    }

    @GetMapping("doctor-specialty/{specialtyId}")
    @Operation(summary = "List doctors by specialty")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@PathVariable int specialtyId) {
        return ResponseEntity.ok(doctorService.findDoctorBySpecialty(specialtyId));
    }

    @GetMapping("doctor-except-deleted")
    @Operation(summary = "List doctor except deleted")
    public ResponseEntity<List<Doctor>> getAllDoctorExceptDeleted() {
        return ResponseEntity.ok(doctorService.findAllDoctorExceptDeleted());
    }

    @GetMapping("doctor-with-appointment-status")
    @Operation(summary = "List doctor except deleted")
    public ResponseEntity<Map<String,List<AppointmentStatus>>> getDoctorsWithAppointmentStatus() {
        return ResponseEntity.ok(doctorService.findDoctorsWithAppointmentStatus());
    }

    @GetMapping("doctor-id/{Id}")
    @Operation(summary = "doctor Id")
    public ResponseEntity<Doctor> getDoctorId( @PathVariable Integer Id) {
        return ResponseEntity.ok(doctorService.findByDoctorId(Id));
    }
    @PostMapping("doctor")
    @Operation(summary = "Save doctor")
    public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody DoctorRequest doctorRequest){
        return ResponseEntity.ok(doctorService.saveDoctor(doctorRequest));
    }
    @PutMapping("doctor/{Id}")
    @Operation(summary = "update doctor")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int Id, @Valid @RequestBody DoctorRequest doctorRequest){
        return ResponseEntity.ok(doctorService.updateDoctor(Id, doctorRequest));
    }

    @DeleteMapping("doctor/{Id}")
    @Operation(summary = "delete doctor")
    public ResponseEntity<MessageResponse> deleteDoctor(@PathVariable int Id){
        return ResponseEntity.ok(doctorService.delete(Id));
    }

    @DeleteMapping("soft-delete-doctor/{Id}")
    @Operation(summary = "delete soft doctor")
    public ResponseEntity<MessageResponse> softDeleteDoctor(@PathVariable int Id){
        return ResponseEntity.ok(doctorService.softDeleteDoctor(Id));
    }
}
