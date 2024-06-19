package com.example.spring_project.webstore.controller;

import com.example.spring_project.webstore.service.FileSystemStorageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileUploadController {

    private final FileSystemStorageService fileSystemStorageService;

    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file")MultipartFile file /*,HttpServletResponse response*/) /*throws IOException*/ {
        fileSystemStorageService.store(file);
        //response.sendRedirect("/admin");
    }

    @GetMapping("/all-image")
    public List<String> listUploadFiles() {
        return fileSystemStorageService.loadAll().map(
                        path -> path.getFileName().toString()).collect(Collectors.toList());
    }

}
