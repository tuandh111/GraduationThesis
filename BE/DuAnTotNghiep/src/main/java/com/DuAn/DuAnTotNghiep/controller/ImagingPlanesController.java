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
    @GetMapping("imaging-planes")
    @Operation(summary = "List imagingPlanes")
    public ResponseEntity<List<ImagingPlanes>> getAllImagingPlanes() {
        return ResponseEntity.ok(imagingPlanesService.findAllImagingPlanes());
    }

    @GetMapping("imaging-planes-except-deleted")
    @Operation(summary = "List imagingPlanes except deleted")
    public ResponseEntity<List<ImagingPlanes>> getAllImagingPlanesExceptDeleted() {
        return ResponseEntity.ok(imagingPlanesService.findAllImagingPlanesExceptDeleted());
    }

    @GetMapping("imaging-planes-id/{Id}")
    @Operation(summary = "dental imagingPlanes Id")
    public ResponseEntity<ImagingPlanes> getImagingPlanes( @PathVariable Integer Id) {
        return ResponseEntity.ok(imagingPlanesService.findByImagingPlanesId(Id));
    }
    @PostMapping("imaging-planes")
    @Operation(summary = "Save imagingPlanes")
    public ResponseEntity<ImagingPlanes> saveImagingPlanes(@Valid @RequestBody ImagingPlanesRequest imagingPlanesRequest){
        return ResponseEntity.ok(imagingPlanesService.saveImagingPlanes(imagingPlanesRequest));
    }
    @PutMapping("imaging-planes/{Id}")
    @Operation(summary = "update imagingPlanes")
    public ResponseEntity<ImagingPlanes> updateImagingPlanes(@PathVariable int Id, @Valid @RequestBody ImagingPlanesRequest imagingPlanesRequest){
        return ResponseEntity.ok(imagingPlanesService.updateImagingPlanes(Id, imagingPlanesRequest));
    }

    @DeleteMapping("imaging-planes/{Id}")
    @Operation(summary = "delete ImagingPlanes")
    public ResponseEntity<MessageResponse> deleteImagingPlanes(@PathVariable int Id){
        return ResponseEntity.ok(imagingPlanesService.delete(Id));
    }

    @DeleteMapping("soft-delete-imaging-planes/{Id}")
    @Operation(summary = "soft delete ImagingPlanes")
    public ResponseEntity<MessageResponse> softDeleteImagingPlanes(@PathVariable int Id){
        return ResponseEntity.ok(imagingPlanesService.softDeletePlanesService(Id));
    }
}
