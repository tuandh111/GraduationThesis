package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.ShiftService;
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
public class ShiftController {
    @Autowired
    ShiftService shiftService;
    @GetMapping("shift")
    @Operation(summary = "List shift")
    public ResponseEntity<List<Shift>> getAllShift() {
        return ResponseEntity.ok(shiftService.findAll());
    }

    @GetMapping("shift-id/{Id}")
    @Operation(summary = "ShiftId")
    public ResponseEntity<Shift> getShiftId( @PathVariable Integer Id) {
        return ResponseEntity.ok(shiftService.findByShiftId(Id));
    }
    @PostMapping("shift")
    @Operation(summary = "Save shift")
    public ResponseEntity<Shift> saveShift(@Valid @RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(shiftService.saveShift(shiftRequest));
    }
    @PutMapping("shift/{Id}")
    @Operation(summary = "update shift")
    public ResponseEntity<Shift> updateShift(@PathVariable int Id, @Valid @RequestBody ShiftRequest shiftRequest){
        return ResponseEntity.ok(shiftService.updateShift(Id, shiftRequest));
    }

    @DeleteMapping("shift/{Id}")
    @Operation(summary = "delete shift")
    public ResponseEntity<MessageResponse> deleteShift(@PathVariable int Id){
        return ResponseEntity.ok(shiftService.delete(Id));
    }

    @DeleteMapping("sort-delete-shift/{Id}")
    @Operation(summary = "delete sort shift")
    public ResponseEntity<MessageResponse> sortDeleteShift(@PathVariable int Id){
        return ResponseEntity.ok(shiftService.sortDeleteShift(Id));
    }
}