package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.utils.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/upload-image-cloudinary")
    public ResponseEntity<MessageResponse> uploadImageWithPublicId(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("publicId") String publicId) throws IOException {
        String imageUrl = cloudinaryService.uploadImageWithPublicId(file,publicId);
        return ResponseEntity.ok(new MessageResponse(imageUrl));
    }

    @PutMapping("/update-image-cloudinary")
    public ResponseEntity<MessageResponse> updateImageWithPublicId(
            @RequestParam("file") MultipartFile file,
            @RequestParam("publicId") String publicId) throws IOException {

        String updatedImageUrl = cloudinaryService.updateImageWithPublicId(file, publicId);
        return ResponseEntity.ok(new MessageResponse(updatedImageUrl));
    }

    @GetMapping("/get-profile-image/{publicId}")
    public ResponseEntity<String> getDoctorProfileImage(@PathVariable String publicId) {

        return ResponseEntity.ok(cloudinaryService.getProfileImage(publicId));
    }

}
