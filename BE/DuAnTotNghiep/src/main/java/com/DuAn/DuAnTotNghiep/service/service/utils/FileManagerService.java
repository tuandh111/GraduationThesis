package com.DuAn.DuAnTotNghiep.service.service.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface FileManagerService {
    byte[] read(String folder, String name);

    byte[] readImg(String folder, String name);

    List<String> save(String folder, MultipartFile[] files);

    Path getPath(String folder, String filename);

    void delete(String folder, String filename);

    void move(String folder);

    void moveTempFolder(String tempFolder, String realFolder);


    boolean hasFiles(Path directory) throws IOException;

    byte[] readImgProd(String folder, String name);

    List<String> list(String folder);
}
