package com.lab.practice.controllers;

import com.lab.practice.service.CountingService;
import com.lab.practice.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountingControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CountingService countingService;
    @MockBean
    private StorageService storageService;

    private String fileName = "controllerTestFile.csv";
    private String columnName = "budget";


    @Test
    void getMaxValue() throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);

        long expectedMaxValue = countingService.maxValue(fileName, "budget");

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("fileName",fileName);
        map.add("columnName","budget");

        ResponseEntity<Long> entity = restTemplate.getForEntity("/maxValue?fileName="+ fileName +"&columnName=" + columnName, Long.class, map);
        Long result = entity.getBody();

        Assertions.assertTrue(entity.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(expectedMaxValue,result);

    }

    @Test
    void getSum() throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("fileName",fileName);
        map.add("columnName","budget");

        long expectedSum = countingService.sum(fileName, columnName);

        ResponseEntity<Long> entity = restTemplate.getForEntity("/sum?fileName="+ fileName +"&columnName=" + columnName, Long.class, map);
        Long result = entity.getBody();

        Assertions.assertTrue(entity.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(expectedSum, result);

    }
}