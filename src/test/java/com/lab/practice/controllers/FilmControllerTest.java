package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.entity.FilmList;
import com.lab.practice.service.CsvParceService;
import com.lab.practice.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    CsvParceService csvParceService;

    @MockBean
    private StorageService storageService;

    @Test
    public void shouldReturnFilmList() throws IOException {

        String fileName = "controllerTestFile.csv";
        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);

        List<Film> expectedFilmList = csvParceService.parseCsvFile(fileName);

        Map<String, String> map = new HashMap<>();
        map.put("fileName", fileName);

        ResponseEntity<FilmList> response = restTemplate.getForEntity("/films?fileName=" + fileName, FilmList.class, fileName);
        FilmList body = response.getBody();
        assert body != null;
        List<Film> resultFilmList = body.getFilmList();

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(expectedFilmList,resultFilmList);
    }


}

