package com.lab.practice.controllers;

import com.lab.practice.service.CsvParceService;
import com.lab.practice.service.StorageService;
import org.junit.jupiter.api.Test;
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

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileUploadControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StorageService storageService;
    @MockBean
    CsvParceService csvParceService;

    @Test
    public void shouldDownloadFile() throws Exception {

        ClassPathResource resource = new ClassPathResource("testFile.csv", getClass());
        given(storageService.downloadFile("testFile.csv")).willReturn(resource);

        Map<String, Object> map = new HashMap<>();
        map.put("fileName", "testFile.csv");
        ResponseEntity<String> response = restTemplate.getForEntity("/download", String.class, map);

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody().equalsIgnoreCase("Spring Test File"));
    }

    @Test
    public void shouldUploadFile() throws Exception {

        ClassPathResource resource = new ClassPathResource("testFile.csv", getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("file", resource);

        ResponseEntity response = restTemplate.postForEntity("/upload", map, Object.class);
        assertThat(response.getStatusCode().is2xxSuccessful());

        then(storageService).should().fileUpload(any(MultipartFile.class));

    }


}