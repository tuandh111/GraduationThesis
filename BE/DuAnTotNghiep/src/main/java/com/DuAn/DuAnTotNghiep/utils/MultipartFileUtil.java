package com.DuAn.DuAnTotNghiep.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MultipartFileUtil {
    public static MultipartFile createFile(byte[] content, String name, String originalFileName, String contentType) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(content);
        try {
            return new MockMultipartFile(name, originalFileName, contentType, inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
