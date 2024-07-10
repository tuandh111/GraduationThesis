package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
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

    @GetMapping("time-of-shift-available")
    @Operation(summary = "time of shift available")
    public ResponseEntity<List<Object>> getTimeOfShiftAvailable(@RequestParam("shiftId") Integer shiftId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam("doctorId") Integer doctorId) {
        System.out.println(date);
        System.out.println(shiftId);
        System.out.println(doctorId);
        return ResponseEntity.ok(timeOfShiftService.findAllTimeOfShiftAvailable(shiftId,date,doctorId));
    }

    @GetMapping("time-of-shift-details")
    @Operation(summary = "All time of shift")
    public ResponseEntity<List<Object>> getAllTimeOfShiftDetails(@RequestParam("shiftId") Integer shiftId,
                                                                @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam("doctorId") Integer doctorId) {
        return ResponseEntity.ok(timeOfShiftService.findAllTimeOfShiftDetails(shiftId,date,doctorId));
    }

    @GetMapping("time-of-shift-by-range")
    @Operation(summary = "Time Of Shift By Range")
    public ResponseEntity<List<TimeOfShift>> getTimeOfShiftByRangeTime(@RequestParam("startStr") LocalTime startStr,
                                                                 @RequestParam("endStr") LocalTime endStr) {
        return ResponseEntity.ok(timeOfShiftService.findTimeOfShiftByRangeTime(startStr,endStr));
    }


    @GetMapping("time-of-shift-available-by-month")
    @Operation(summary = "time of shift available by month")
    public ResponseEntity<List<Object>> getTimeOfShiftAvailableByMonth(
                                                                @RequestParam("month") Integer month, @RequestParam("year") Integer year,@RequestParam("doctorId") Integer doctorId) {
        return ResponseEntity.ok(timeOfShiftService.getAvailableShiftsByMonth(doctorId,month,year));
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

    @DeleteMapping("soft-delete-time-of-shift/{Id}")
    @Operation(summary = "delete soft time-of-shift")
    public ResponseEntity<MessageResponse> softDeleteTimeOfShift(@PathVariable int Id){
        return ResponseEntity.ok(timeOfShiftService.softDeleteTimeOfShift(Id));
    }
}
