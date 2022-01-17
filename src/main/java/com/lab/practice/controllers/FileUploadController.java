package com.lab.practice.controllers;

import com.lab.practice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping(value = "/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) {

        fileStorageService.fileUpload(file);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {

        Resource file = fileStorageService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
