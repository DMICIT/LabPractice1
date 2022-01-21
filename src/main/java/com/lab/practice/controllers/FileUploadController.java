package com.lab.practice.controllers;

import com.lab.practice.entity.FileBucket;
import com.lab.practice.service.FileStorageService;
import com.lab.practice.service.StorageService;
import com.lab.practice.validation.FileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
public class FileUploadController {

    @Autowired
    StorageService fileStorageService;
    @Autowired
    FileValidation fileValidation;

    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@Valid FileBucket file, BindingResult errors) {

        fileValidation.validate(file, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        fileStorageService.fileUpload(file.getFile());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {

        Resource file = fileStorageService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
