package com.DuAn.DuAnTotNghiep.service.impl.utils;

import com.DuAn.DuAnTotNghiep.service.service.utils.FileManagerService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileManagerServiceImpl implements FileManagerService {
    @Autowired
    ServletContext app;

    @Override
    public byte[] read(String folder, String name) {
        Path path = this.getPath(folder, name);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] readImg(String folder, String name) {
        byte[] imageData = null;
        Path imagePath = Paths.get("", "files", folder, name);
        try {
            imageData = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file: " + imagePath.toString(), e);
        }

        return imageData;
    }

    @Override
    public List<String> save(String folder, MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = this.getPath(folder, filename);
            try {
                file.transferTo(path);
                fileNames.add(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    @Override
    public List<String> saveCTResult(String folder, MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        Path uploadImagePath = Paths.get("", "files", folder);
        try {
            Files.createDirectories(uploadImagePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + uploadImagePath.toString(), e);
        }
        for (MultipartFile file : files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = uploadImagePath.resolve(filename);

            try {
                file.transferTo(path);
                fileNames.add(filename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save file: " + filename, e);
            }
        }

        return fileNames;
    }

    @Override
    public Path getPath(String folder, String filename) {
        File dir = Paths.get(app.getRealPath("/files/"), folder).toFile();
        if (!dir.exists()) {
            boolean created = dir.mkdirs();

            if (!created) {
                throw new RuntimeException("Failed to create directory: " + dir.getAbsolutePath());
            }
        }
        return Paths.get(dir.getAbsolutePath(), filename);

    }

    @Override
    public void delete(String folder, String name) {
        Path path = this.getPath(folder, name);
        path.toFile().delete();
    }

    @Override
    public void move(String folder) {
        // Path uploadImagePath = Paths.get(app.getRealPath("/files/"), "uploadImage");
        Path projectRoot = Paths.get(""); // Đây là một đường dẫn tương đối trỏ đến thư mục gốc của dự án

        // Xác định đường dẫn của thư mục uploadImage trong dự án
        Path uploadImagePath = projectRoot.resolve("files").resolve("uploadImage");
        // Kiểm tra nếu thư mục uploadImage không tồn tại thì tạo mới
        if (!Files.exists(uploadImagePath)) {
            try {
                Files.createDirectories(uploadImagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory: " + uploadImagePath.toString(), e);
            }
        }

        // Xác định đường dẫn của thư mục gốc
        Path sourceFolderPath = Paths.get(app.getRealPath("/files/"), folder);
        try {
            if (!Files.exists(sourceFolderPath) || !Files.isDirectory(sourceFolderPath) || !hasFiles(sourceFolderPath)) {
                return; // Không cần di chuyển nếu thư mục gốc không tồn tại hoặc rỗng
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Di chuyển tất cả các tệp từ thư mục gốc sang thư mục uploadImage
        try {
            Files.walk(sourceFolderPath).filter(Files::isRegularFile).forEach(sourceFile -> {
                try {
                    // Xác định đường dẫn đích cho tệp
                    Path targetFile = uploadImagePath.resolve(sourceFolderPath.relativize(sourceFile));
                    // Di chuyển tệp
                    Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to move file: " + sourceFile.toString(), e);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException("Failed to walk directory: " + sourceFolderPath.toString(), e);
        }
    }

    @Override
    public void moveTempFolder(String tempFolder, String realFolder) {
        Path projectRoot = Paths.get("");

        Path uploadImagePath = projectRoot.resolve("files").resolve(realFolder);

        if (!Files.exists(uploadImagePath)) {
            try {
                Files.createDirectories(uploadImagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory: " + uploadImagePath.toString(), e);
            }
        }

        // Xác định đường dẫn của thư mục gốc
        Path sourceFolderPath = Paths.get(app.getRealPath("/files/"), tempFolder);
        try {
            if (!Files.exists(sourceFolderPath) || !Files.isDirectory(sourceFolderPath) || !hasFiles(sourceFolderPath)) {
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.walk(sourceFolderPath).filter(Files::isRegularFile).forEach(sourceFile -> {
                try {

                    Path targetFile = uploadImagePath.resolve(sourceFolderPath.relativize(sourceFile));

                    Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to move file: " + sourceFile.toString(), e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to walk directory: " + sourceFolderPath.toString(), e);
        }
    }


    @Override
    public boolean hasFiles(Path directory) throws IOException {
        try (Stream<Path> stream = Files.list(directory)) {
            return stream.findFirst().isPresent();
        }
    }

    @Override
    public byte[] readImgProd(String folder, String name) {
        byte[] imageData = null;

        // Đường dẫn đến file hình ảnh trong thư mục uploadImage
        Path imagePath = Paths.get("", "files", folder, name);

        try {
            // Đọc dữ liệu của hình ảnh
            imageData = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file: " + imagePath.toString(), e);
        }

        return imageData;
    }

    @Override
    public List<String> list(String folder) {
        List<String> fileNames = new ArrayList<>();
        File dir = Paths.get(app.getRealPath("/files"), folder).toFile();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileNames.add(file.getName());
            }

        }
        return fileNames;
    }


}
