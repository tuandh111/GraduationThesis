package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.TimeOfShiftService;
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
public class TimeOfShiftController {
    @Autowired
    TimeOfShiftService timeOfShiftService;
    @GetMapping("shift")
    @Operation(summary = "List  timeOfShift")
    public ResponseEntity<List<Shift>> getAllTimeOfShift() {
        return ResponseEntity.ok(timeOfShiftService.findAll());
    }

    @GetMapping("shift-id/{Id}")
    @Operation(summary = "ShiftId")
    public ResponseEntity<Shift> getShiftId( @PathVariable Integer Id) {
        return ResponseEntity.ok(timeOfShiftService.findByShiftId(Id));
    }
    @PostMapping("shift")
    @Operation(summary = "Save shift")
    public ResponseEntity<Shift> saveShift(@Valid @RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(timeOfShiftService.saveShift(shiftRequest));
    }
    @PutMapping("shift/{Id}")
    @Operation(summary = "update shift")
    public ResponseEntity<Shift> updateShift(@PathVariable int Id, @Valid @RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(timeOfShiftService.updateShift(Id, shiftRequest));
    }

    @DeleteMapping("shift/{Id}")
    @Operation(summary = "delete shift")
    public ResponseEntity<MessageResponse> deleteShift(@PathVariable int Id){
        return ResponseEntity.ok(timeOfShiftService.delete(Id));
    }

    @DeleteMapping("sort-delete-shift/{Id}")
    @Operation(summary = "delete sort shift")
    public ResponseEntity<MessageResponse> sortDeleteShift(@PathVariable int Id){
        return ResponseEntity.ok(timeOfShiftService.sortDeleteShift(Id));
    }
}
