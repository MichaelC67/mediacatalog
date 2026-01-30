package com.example.mediacatalog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtil {

    private final Path uploadBasePath;

    public FileUploadUtil(@Value("${app.upload.dir:uploads}") String uploadDir) {
        this.uploadBasePath = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = uploadBasePath;

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName).normalize();
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
