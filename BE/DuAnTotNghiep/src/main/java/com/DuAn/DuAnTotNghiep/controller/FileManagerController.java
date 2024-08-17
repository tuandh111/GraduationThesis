package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.service.service.utils.FileManagerService;
import jakarta.websocket.server.PathParam;
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
    FileManagerService fileManagerService;

    @GetMapping("image/{folder}/{file}")
    public byte[] dowmload(@PathVariable("folder") String folder, @PathVariable("file") String file) {
        return fileManagerService.read(folder, file);
    }

    @GetMapping("listImage/{folder}")
    public List<String> list(@PathVariable("folder") String folder) {
        return fileManagerService.list(folder);
    }

    @PostMapping("saveImage/{folder}")
    public List<String> upload(@PathVariable("folder") String folder, @RequestParam("files") MultipartFile[] files) {
        return fileManagerService.save(folder, files);
    }

    @DeleteMapping("deleteImage/{folder}/{file}")
    public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
        fileManagerService.delete(folder, file);
    }

    @PostMapping("moveImage/{folder}")
    public void move(@PathVariable("folder") String folder) {
        System.out.println("move folder " + folder);
        fileManagerService.move(folder);
    }

    @GetMapping("uploadImage/{file}")
    public byte[] dowmloadImagesForProduct(@PathVariable("file") String file) {
        System.out.println("file: "+ file);
        String folder = "uploads"; // Đọc tệp từ thư mục uploadImage
        return fileManagerService.readImgProd(folder, file);
    }

    @GetMapping("uploadImage")
    public List<String> listUploadImage() {
        String folder = "uploadImage";
        return fileManagerService.list(folder);
    }

    @GetMapping("/{folder}/{file}")
    public byte[] dowmloadImages(@PathVariable("folder") String folder,@PathVariable("file") String file) {
        //String folder = "uploadImage";
        return fileManagerService.readImg(folder, file);
    }

    @GetMapping("/{folder}")
    public  List<String> listFolderUploadImage (@PathVariable("folder") String folder){
        //String folder = "uploadImage";
        return fileManagerService.list(folder);
    }

    @PostMapping("/move/{tempFolder}/{realFolder}")
    public void moveTempFolder(@PathVariable("tempFolder") String tempFolder, @PathVariable("realFolder") String realFolder) {
        fileManagerService.moveTempFolder(tempFolder,realFolder);
    }
}
