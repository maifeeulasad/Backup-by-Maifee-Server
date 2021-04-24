package com.mua.backupbymaifee.controller;

import com.mua.backupbymaifee.model.File;
import com.mua.backupbymaifee.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/file/upload/")
    Boolean upload(
            @RequestPart("absolute_path") String absolutePath,
            @RequestPart MultipartFile file) {
        return service.upload(absolutePath, file);
    }

    @GetMapping("/file/all")
    List<File> all(){
        return service.all();
    }

}
