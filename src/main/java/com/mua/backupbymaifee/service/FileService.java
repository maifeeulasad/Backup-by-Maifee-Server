package com.mua.backupbymaifee.service;

import com.mua.backupbymaifee.repository.FileRepository;
import com.mua.backupbymaifee.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileService {

    @Value("${mua.backup-dir}")
    private String backupDir;

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Boolean upload(
            @RequestPart("absolute_path") String absolutePath,
            @RequestPart MultipartFile multipartFile) {
        try {
            String filepath = backupDir + absolutePath;
            filepath = FileUtil.getAvailablePath(filepath);
            File file = new File(filepath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            OutputStream os = Files.newOutputStream(Path.of(filepath));
            os.write(multipartFile.getBytes());
            com.mua.backupbymaifee.model.File fileTem
                    = new com.mua.backupbymaifee.model.File();
            fileTem.setOriginalAbsolutePath(absolutePath);
            fileTem.setAbsolutePath(filepath);
            fileRepository.save(fileTem);
            return true;
        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
            return false;
        }
    }

    public List<com.mua.backupbymaifee.model.File> all() {
        return fileRepository.findAll();
    }

}
