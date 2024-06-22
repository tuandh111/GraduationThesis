package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.request.TimeOfShiftRequest;
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
    @GetMapping("time-of-shift")
    @Operation(summary = "List  timeOfShift")
    public ResponseEntity<List<TimeOfShift>> findAllTimeOfShift() {
        return ResponseEntity.ok(timeOfShiftService.findAllTimeOfShift());
    }

    @GetMapping("time-of-shift-except-deleted")
    @Operation(summary = "List  timeOfShift except deleted")
    public ResponseEntity<List<TimeOfShift>> findAllTimeOfShiftExceptDeleted() {
        return ResponseEntity.ok(timeOfShiftService.findAllTimeOfShiftExceptDeleted());
    }

    @GetMapping("time-of-shift/{Id}")
    @Operation(summary = "time-of-shift Id")
    public ResponseEntity<TimeOfShift> getTimeOfShiftId( @PathVariable Integer Id) {
        return ResponseEntity.ok(timeOfShiftService.findByTimeOfShiftId(Id));
    }
    @GetMapping("time-of-shift-by-shift-id/{Id}")
    @Operation(summary = "time-of-shift Id")
    public ResponseEntity<List<TimeOfShift>> getTimeOfShiftByShiftId( @PathVariable Integer Id) {
        return ResponseEntity.ok(timeOfShiftService.findAllTimeOfShiftByShift(Id));
    }
    @PostMapping("time-of-shift")
    @Operation(summary = "Save time-of-shift")
    public ResponseEntity<TimeOfShift> saveTimeOfShift(@Valid @RequestBody TimeOfShiftRequest timeOfShiftRequest){
        return ResponseEntity.ok(timeOfShiftService.saveTimeOfShift(timeOfShiftRequest));
    }
    @PutMapping("time-of-shift/{Id}")
    @Operation(summary = "update time-of-shift")
    public ResponseEntity<TimeOfShift> updateTimeOfShift(@PathVariable int Id, @Valid @RequestBody TimeOfShiftRequest timeOfShiftRequest){
        return ResponseEntity.ok(timeOfShiftService.updateTimeOfShift(Id, timeOfShiftRequest));
    }

    @DeleteMapping("time-of-shift/{Id}")
    @Operation(summary = "delete time-of-shift")
    public ResponseEntity<MessageResponse> deleteTimeOfShift(@PathVariable int Id){
        return ResponseEntity.ok(timeOfShiftService.delete(Id));
    }

    @DeleteMapping("sort-delete-time-of-shift/{Id}")
    @Operation(summary = "delete sort time-of-shift")
    public ResponseEntity<MessageResponse> sortDeleteTimeOfShift(@PathVariable int Id){
        return ResponseEntity.ok(timeOfShiftService.sortDeleteTimeOfShift(Id));
    }
}
