package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.service.FileSystemStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileUploadController {

    private final FileSystemStorageService fileSystemStorageService;

    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file")MultipartFile file) {
        fileSystemStorageService.store(file);
    }

}
