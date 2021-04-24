package com.mua.backupbymaifee.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    @Value("${mua.backup-dir}")
    private String backupDir;

    public Boolean upload(
            @RequestPart("absolute_path") String absolutePath,
            @RequestPart MultipartFile multipartFile) {
        try {
            String filepath = backupDir + absolutePath;
            File file = new File(filepath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            OutputStream os = Files.newOutputStream(Path.of(filepath));
            os.write(multipartFile.getBytes());
            //System.out.println(filepath);
            return true;
        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
            return false;
        }
    }

}
