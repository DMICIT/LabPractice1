package com.lab.practice.controllers;

import com.lab.practice.service.CountingService;
import com.lab.practice.service.StorageService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountingControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CountingService countingService;



    @Test
    void getMaxValue() {

        given(countingService.maxValue("controllerTestFile.csv","budget")).willReturn(10l);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("fileName","controllerTestFile.csv");
        map.add("columnName","budget");

        ResponseEntity<Long> entity = restTemplate.getForEntity("/maxValue?fileName=IMDb_Movie_Database.csv&columnName=budget", Long.class, map);
        assertThat(entity.getStatusCode().is2xxSuccessful());
        assertThat(entity.getBody().equals(10l));


    }

    @Test
    void getSum() {

        given(countingService.sum("controllerTestFile.csv","budget")).willReturn(20L);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("fileName","controllerTestFile.csv");
        map.add("columnName","budget");

        ResponseEntity<Long> entity = restTemplate.getForEntity("/sum?fileName=IMDb_Movie_Database.csv&columnName=budget", Long.class, map);
        assertThat(entity.getStatusCode().is2xxSuccessful());
        assertThat(entity.getBody().equals(20L));

    }
}