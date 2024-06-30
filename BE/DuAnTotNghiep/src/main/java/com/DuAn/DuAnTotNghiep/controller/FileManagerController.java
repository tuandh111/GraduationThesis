package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.service.service.utils.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class FileManagerController {
    @Autowired
    private FileManagerService fileManagerService;
    @GetMapping("/read/{folder}/{name}")
    public ResponseEntity<byte[]> readFile(@PathVariable String folder, @PathVariable String name) {
        try {
            byte[] fileData = fileManagerService.read(folder, name);
            return ResponseEntity.ok(fileData);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }
        try {
            // Tạo đường dẫn lưu trữ hình ảnh
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/CTResult/" + fileName);

            // Lưu hình ảnh vào thư mục
            Files.write(path, file.getBytes());

            // Trả về tên tệp để lưu vào cơ sở dữ liệu
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
    @PostMapping("/save/{folder}")
    public ResponseEntity<List<String>> saveFiles(@PathVariable String folder, @RequestParam MultipartFile[] files) {
        try {
            List<String> fileNames = fileManagerService.save(folder, files);
            return ResponseEntity.ok(fileNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/delete/{folder}/{name}")
    public ResponseEntity<Void> deleteFile(@PathVariable String folder, @PathVariable String name) {
        try {
            fileManagerService.delete(folder, name);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/move/{folder}")
    public ResponseEntity<Void> moveFiles(@PathVariable String folder) {
        try {
            fileManagerService.move(folder);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/list/{folder}")
    public ResponseEntity<List<String>> listFiles(@PathVariable String folder) {
        try {
            List<String> fileNames = fileManagerService.list(folder);
            return ResponseEntity.ok(fileNames);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
