package com.lab.practice.controllers;

import com.lab.practice.entity.FileBucket;
import com.lab.practice.service.CsvParceService;
import com.lab.practice.service.StorageService;
import com.lab.practice.validation.FileValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
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
    private FileValidation validation;
    @MockBean
    private StorageService storageService;
    @MockBean
    CsvParceService csvParceService;

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
    public void shouldUploadFile() {

        ClassPathResource resource = new ClassPathResource(filename, getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);

        ArgumentCaptor<MultipartFile> captor = ArgumentCaptor.forClass(MultipartFile.class);
        ResponseEntity<Object> response = restTemplate.postForEntity("/upload", map, Object.class);
        verify(storageService).fileUpload(captor.capture());

        String resultFilename = captor.getValue().getOriginalFilename();
        String contentType = captor.getValue().getContentType();

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(filename,resultFilename);
        Assertions.assertEquals("text/csv", contentType);

    }

    @Test
    public void shouldValidateFile(){

        ArgumentCaptor<FileBucket> captor = ArgumentCaptor.forClass(FileBucket.class);
        ClassPathResource resource = new ClassPathResource(filename, getClass());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);
        ResponseEntity<Object> response = restTemplate.postForEntity("/upload", map, Object.class);

        verify(validation).validate(captor.capture(),any(BindingResult.class));
        FileBucket value = captor.getValue();
        String resultFilename = value.getFile().getOriginalFilename();

        Assertions.assertEquals(filename,resultFilename);
    }

}