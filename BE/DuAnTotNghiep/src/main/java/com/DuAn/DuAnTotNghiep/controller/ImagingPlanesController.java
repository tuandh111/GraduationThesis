package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.ImagingPlanes;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.ImagingPlanesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.ImagingPlanesService;
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
public class ImagingPlanesController {
    @Autowired
    ImagingPlanesService imagingPlanesService;
    @GetMapping("list-imagingPlanes")
    @Operation(summary = "List imagingPlanes")
    public ResponseEntity<List<ImagingPlanes>> getAllImagingPlanes() {
        return ResponseEntity.ok(imagingPlanesService.findAll());
    }

    @GetMapping("imagingPlanes-id/{Id}")
    @Operation(summary = "dental imagingPlanes Id")
    public ResponseEntity<ImagingPlanes> getImagingPlanes( @PathVariable Integer Id) {
        return ResponseEntity.ok(imagingPlanesService.findByImagingPlanesId(Id));
    }
    @PostMapping("save-imagingPlanes")
    @Operation(summary = "Save imagingPlanes")
    public ResponseEntity<ImagingPlanes> saveImagingPlanes(@Valid @RequestBody ImagingPlanesRequest imagingPlanesRequest){
        return ResponseEntity.ok(imagingPlanesService.saveImagingPlanes(imagingPlanesRequest));
    }
    @PutMapping("imagingPlanes/{Id}")
    @Operation(summary = "update imagingPlanes")
    public ResponseEntity<ImagingPlanes> updateImagingPlanes(@PathVariable int Id, @Valid @RequestBody ImagingPlanesRequest imagingPlanesRequest){
        return ResponseEntity.ok(imagingPlanesService.updateImagingPlanes(Id, imagingPlanesRequest));
    }

    @DeleteMapping("delete-imagingPlanes/{Id}")
    @Operation(summary = "delete ImagingPlanes")
    public ResponseEntity<MessageResponse> deleteImagingPlanes(@PathVariable int Id){
        return ResponseEntity.ok(imagingPlanesService.delete(Id));
    }
}
