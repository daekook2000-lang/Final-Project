package org.cloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    public String upload(MultipartFile file, String folder) throws IOException {
        String ext = getExtension(file.getOriginalFilename());
        String savedName = UUID.randomUUID() + ext;

        String uploadDir = "C:/upload/uploads/" + folder + "/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        File target = new File(uploadDir + savedName);
        file.transferTo(target);

        return "uploads/" + folder + "/" + savedName;
    }

    public void delete(String key) {
        File file = new File("C:/upload/" + key);
        if (file.exists()) file.delete();
    }

    private String getExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf("."));
        }
        return "";
    }
}