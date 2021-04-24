package com.mua.backupbymaifee.controller;

import com.mua.backupbymaifee.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/upload/")
    Boolean upload(
            @RequestPart("absolute_path") String absolutePath,
            @RequestPart MultipartFile file){
        return fileService.upload(absolutePath, file);
    }

}
