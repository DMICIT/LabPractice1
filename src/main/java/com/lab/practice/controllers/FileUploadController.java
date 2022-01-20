package com.lab.practice.controllers;

import com.lab.practice.service.FileStorageService;
import com.lab.practice.validation.FileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    FileValidation fileValidation;

    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, Errors errors) {

        fileValidation.validate(file, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        fileStorageService.fileUpload(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {

        Resource file = fileStorageService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
