package com.DuAn.DuAnTotNghiep.service.impl.utils;

import com.DuAn.DuAnTotNghiep.service.service.utils.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    @Override
    public String uploadImageWithPublicId(MultipartFile file, String publicId) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("public_id", publicId));
        return (String) uploadResult.get("public_id");
    }

    @Override
    public String updateImageWithPublicId(MultipartFile file, String publicId) throws IOException {

        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
       // Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", publicId));
        //return (String) uploadResult.get("url");
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("public_id", publicId));
        return (String) uploadResult.get("public_id");
    }

    @Override
    public String getProfileImage(String publicId) {
        return cloudinary.url().publicId(publicId).format("jpg").generate();
    }
}