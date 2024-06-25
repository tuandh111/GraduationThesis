package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.AppointmentType;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentTypeRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentTypeService;
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
public class AppointmentTypeController {
    @Autowired
    AppointmentTypeService appointmentTypeService;
    @GetMapping("appointment-type")
    @Operation(summary = "List appointment type")
    public ResponseEntity<List<AppointmentType>> getAllAppointmentType() {
        return ResponseEntity.ok(appointmentTypeService.findAllAppointmentType());
    }

    @GetMapping("appointment-type-except-deleted")
    @Operation(summary = "List appointment type except deleted")
    public ResponseEntity<List<AppointmentType>> getAllAppointmentTypeExceptDeleted() {
        return ResponseEntity.ok(appointmentTypeService.findAllAppointmentTypeExceptDeleted());
    }

    @GetMapping("appointment-type-id/{Id}")
    @Operation(summary = "dental appointment type")
    public ResponseEntity<AppointmentType> getAppointmentTypeId( @PathVariable Integer Id) {
        return ResponseEntity.ok(appointmentTypeService.findByAppointmentTypeId(Id));
    }
    @PostMapping("appointment-type")
    @Operation(summary = "Save appointment type")
    public ResponseEntity<AppointmentType> saveAppointmentType(@Valid @RequestBody AppointmentTypeRequest appointmentTypeRequest){
        return ResponseEntity.ok(appointmentTypeService.saveAppointmentType(appointmentTypeRequest));
    }
    @PutMapping("appointment-type/{Id}")
    @Operation(summary = "update appointment type")
    public ResponseEntity<AppointmentType> updateAppointmentType(@PathVariable int Id, @Valid @RequestBody AppointmentTypeRequest appointmentTypeRequest){
        return ResponseEntity.ok(appointmentTypeService.updateAppointmentType(Id, appointmentTypeRequest));
    }

    @DeleteMapping("appointment-type/{Id}")
    @Operation(summary = "delete appointment type")
    public ResponseEntity<MessageResponse> deleteAppointmentType(@PathVariable int Id){
        return ResponseEntity.ok(appointmentTypeService.delete(Id));
    }

    @DeleteMapping("soft-delete-appointment-type/{Id}")
    @Operation(summary = "delete soft appointment type")
    public ResponseEntity<MessageResponse> softDeleteAppointmentType(@PathVariable int Id){
        return ResponseEntity.ok(appointmentTypeService.softDeleteAppointmentType(Id));
    }
}
