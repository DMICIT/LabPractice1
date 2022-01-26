package com.lab.practice.controllers;

import com.lab.practice.service.CsvParceService;
import com.lab.practice.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileUploadControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StorageService storageService;
    @MockBean
    CsvParceService csvParceService;
    @Captor
    ArgumentCaptor<MultipartFile> file;

    private final String filename = "controllerTestFile.csv";

    @Test
    public void shouldDownloadFile() {

        ClassPathResource resource = new ClassPathResource(filename, getClass());
        given(storageService.downloadFile(filename)).willReturn(resource);

        Map<String, Object> map = new HashMap<>();
        map.put("fileName", filename);
        ResponseEntity<String> response = restTemplate.getForEntity("/download?fileName="+filename, String.class, map);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().startsWith("Title;Release Date;Color/B&W;Genre;Language;Country"));
        Assertions.assertTrue(response.getBody().endsWith("2000000"));

    }

    @Test
    public void shouldUploadFile() throws IOException {

        ClassPathResource resource = new ClassPathResource(filename, getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("file", resource);

        ResponseEntity response = restTemplate.postForEntity("/upload", map, Object.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());

        MultipartFile multipartFile = file.capture();
        Assertions.assertEquals(filename,multipartFile.getOriginalFilename());



    }
}