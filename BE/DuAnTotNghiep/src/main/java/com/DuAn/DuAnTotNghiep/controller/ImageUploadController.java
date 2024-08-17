package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.utils.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class ImageUploadController {
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ImageUploadController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload-cloudinary")
    public ResponseEntity<MessageResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file);
            return ResponseEntity.ok(new MessageResponse(imageUrl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("failed"));
        }
    }
}
