package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Frequency;
import com.DuAn.DuAnTotNghiep.model.request.FrequencyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyService;
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
public class FrequencyController {
    @Autowired
    FrequencyService frequencyService ;

    @GetMapping("frequency")
    @Operation(summary = "List frequencies")
    public ResponseEntity<List<Frequency>> getAllFrequencies() {
        return ResponseEntity.ok(frequencyService.findAllFrequency()) ;
    }

    @GetMapping("frequency-except-deleted")
    @Operation(summary = "List frequencies except deleted")
    public ResponseEntity<List<Frequency>> getAllFrequenciesExceptDeleted() {
        return ResponseEntity.ok(frequencyService.findAllFrequencyExceptDeleted()) ;
    }

    @GetMapping("frequency-id/{Id}")
    @Operation(summary = "Detal frequency Id")
    public ResponseEntity<Frequency> getFrequencyById(@PathVariable Integer Id) {
        return ResponseEntity.ok(frequencyService.findByFrequencyId(Id)) ;
    }

    @PostMapping("frequency")
    @Operation(summary = "Save frequency")
    public ResponseEntity<Frequency> saveFrequency(@Valid @RequestBody FrequencyRequest frequencyRequest) {
        return ResponseEntity.ok(frequencyService.saveFrequency(frequencyRequest)) ;
    }

    @PutMapping("frequency/{Id}")
    @Operation(summary = "Update frequency")
    public ResponseEntity<Frequency> updateFrequency(@PathVariable int Id, @Valid @RequestBody FrequencyRequest frequencyRequest) {
        return ResponseEntity.ok(frequencyService.updateFrequency(Id, frequencyRequest)) ;
    }

    @DeleteMapping("frequency/{Id}")
    @Operation(summary = "Delete frequency")
    public ResponseEntity<MessageResponse> deleteFrequency(@PathVariable int Id) {
        return ResponseEntity.ok(frequencyService.deleteFrequency(Id)) ;
    }

    @DeleteMapping("soft-delete-frequency/{Id}")
    @Operation(summary = "Soft delete frequency")
    public ResponseEntity<MessageResponse> softDeleteFrequency(@PathVariable int Id) {
        return ResponseEntity.ok(frequencyService.softDeleteFrequency(Id)) ;
    }
}

