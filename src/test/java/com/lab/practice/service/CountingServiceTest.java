package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CountingServiceTest {

   @InjectMocks
   CountingService countingService;

    @Mock
    CsvParceService csvParceService;

    @Test
    void maxValue() {

        Film film1 = new Film();
        Film film2 = new Film();
        Film film3 = new Film();
        film1.setRevenue(100L);
        film2.setRevenue(300L);
        film3.setRevenue(500L);

        List<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);
        films.add(film3);

        given(csvParceService.parseCsvFile("testFile.csv")).willReturn(films);

        long maxValue = countingService.maxValue("testFile.csv", "revenue");
        assertEquals(500L,maxValue);

    }

    @Test
    void sum() {

        Film film1 = new Film();
        Film film2 = new Film();
        Film film3 = new Film( );
        film1.setRevenue(100L);
        film2.setRevenue(300L);
        film3.setRevenue(500L);

        List<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);
        films.add(film3);

        given(csvParceService.parseCsvFile("testFile.csv")).willReturn(films);

        long maxValue = countingService.sum("testFile.csv", "revenue");
        long result = film1.getRevenue() + film2.getRevenue() + film3.getRevenue();

        assertEquals(result,maxValue);


    }
}