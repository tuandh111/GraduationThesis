package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.service.service.DateService;
import io.swagger.v3.oas.annotations.Operation;
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
public class DateCategoriesController {

    @Autowired
    DateService dateService;

    @GetMapping("/get-day-of-month")
    @Operation(summary = "Get day of month")
    public ResponseEntity<List<String>> getDateCategories(@RequestParam(value = "moth",required = false) String monthParam){
        return ResponseEntity.ok(dateService.getDateCategories(monthParam));
    }

    @GetMapping("/get-date-data")
    @Operation(summary = "Get time data")
    public ResponseEntity<List<String>> generateMMYYYYData(){
        return ResponseEntity.ok(dateService.generateMMYYYYData());
    }
}
