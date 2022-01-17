package com.lab.practice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    void fileUpload(MultipartFile file);

    Resource downloadFile(String filename);

    Path load(String filename);


}
