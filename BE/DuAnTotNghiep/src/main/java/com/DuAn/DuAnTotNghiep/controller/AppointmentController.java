package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.request.DateRangeRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("appointment-invoice")
    @Operation(summary = "List appointment")
    public ResponseEntity<List<?>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAllAppointmentService());
    }

    @GetMapping("appointment-invoice-cancel")
    @Operation(summary = "List appointment")
    public ResponseEntity<List<?>> getAllBillsCancel() {
        return ResponseEntity.ok(appointmentService.findAllBillCancel());
    }

    @GetMapping("appointment-except-deleted")
    @Operation(summary = "List appointment except deleted")
    public ResponseEntity<List<Appointment>> getAllAppointmentExceptDeleted() {
        return ResponseEntity.ok(appointmentService.findAllAppointmentExceptDeleted());
    }


    @GetMapping("appointment-id/{Id}")
    @Operation(summary = "dental appointment Id")
    public ResponseEntity<Appointment> getAppointmentId(@PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentService.findByAppointmentId(Id));
    }

    @GetMapping("date-of-appointment")
    @Operation(summary = "List date of appointment")
    public ResponseEntity<List<Object>> getAllDateOfAppointment() {
        return ResponseEntity.ok(appointmentService.findAllDateOfAppointment());
    }

    @GetMapping("appointment-by-date")
    @Operation(summary = "List appointment by date")
    public ResponseEntity<List<Appointment>> getAppointmentByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(appointmentService.findAppointmentByDate(date));
    }

    @GetMapping("appointment-by-patient")
    @Operation(summary = "List appointment by patient")
    public ResponseEntity<List<Appointment>> getAllAppByPatient(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date now,
                                                                @RequestParam("patientId") Integer patientId) {
        return ResponseEntity.ok(appointmentService.findAllAppByPatient(now,patientId));
    }

    @GetMapping("appointment-without-bill")
    @Operation(summary = "List appointment without bill")
    public ResponseEntity<List<Appointment>> getAppointmentWithOutBill(@RequestParam(value = "appStatus",required = false) Integer appStatus) {
        return ResponseEntity.ok(appointmentService.findAppointmentWithOutBill(appStatus));
    }

    @GetMapping("appointment-without-ctresult")
    @Operation(summary = "List appointment without bill")
    public ResponseEntity<List<Appointment>> getAppointmentWithOutCtresult() {
        return ResponseEntity.ok(appointmentService.findAppointmentWithOutCtresult());
    }

    @GetMapping("appointment-group-by-date")
    @Operation(summary = "List appointment by date")
    public ResponseEntity<Map<Date, List<Appointment>>> getAllAppGroupByDate(@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam(value = "patientIds", required = false) List<Integer> patientIds, @RequestParam(value = "doctorIds", required = false) List<Integer> doctorIds) {
        return ResponseEntity.ok(appointmentService.findAllAppGroupByDate(startDate, endDate, patientIds, doctorIds));
    }

    @GetMapping("appoinment-by-day-month-year")
    @Operation(summary = "Get list appoinment by day or month or year")
    public ResponseEntity<List<Appointment>> getAppointmentsByDateMonthYear(@RequestParam(value = "day",required = false) Integer day,
                                                                            @RequestParam(value = "month",required = false) Integer month,
                                                                            @RequestParam(value = "year",required = false) Integer year){
        return ResponseEntity.ok(appointmentService.findAppointmentsByDateMonthYear(day,month,year));
    }

    @PostMapping("appointment")
    @Operation(summary = "Save appointment")
    public ResponseEntity<Appointment> saveAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointmentRequest));
    }

    @PostMapping("/appointment-filter")
    public List<AppointmentWithServicesResponse> filterAppointmentsByDateRange(@RequestBody DateRangeRequest dateRangeRequest) {
        return appointmentService.findAllAppointmentService(dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate());
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

    @DeleteMapping("soft-delete-appointment/{Id}")
    @Operation(summary = "delete soft appointment")
    public ResponseEntity<MessageResponse> softDeleteAppointmentId(@PathVariable int Id) {
        return ResponseEntity.ok(appointmentService.softDeleteAppointment(Id));
    }
}
