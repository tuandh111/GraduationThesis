package com.DuAn.DuAnTotNghiep.service.service.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadImage(MultipartFile file) throws IOException;

    String uploadImageWithPublicId(MultipartFile file,String publicId) throws IOException;

    String updateImageWithPublicId(MultipartFile file, String publicId) throws IOException;
    String getProfileImage(String publicId);
}
