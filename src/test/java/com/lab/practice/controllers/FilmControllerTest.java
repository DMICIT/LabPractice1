package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.service.CsvParceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    CsvParceService csvParceService;

    @Test
    public void shouldReturnFilmList() {

            Film film1 = new Film("фильм1","жанр1" );
            Film film2 = new Film("фильм2","жанр2" );
            Film film3 = new Film("фильм3","жанр3" );
            List<Film> films = new ArrayList<>();
            films.add(film1);
            films.add(film2);
            films.add(film3);

            given(csvParceService.parseCsvFile("testFile.csv")).willReturn(films);

            Map<String, String> map = new HashMap<>();
            map.put("fileName", "testFile.csv");
            ResponseEntity<String> response = restTemplate.getForEntity("/films?fileName=testFile.csv", String.class, "testFile.csv");
            assertThat(response.getStatusCode().is2xxSuccessful());
            assertThat(response.getBody()).contains("фильм1");
            assertThat(response.getBody()).contains("фильм2");
            assertThat(response.getBody()).contains("фильм3");
            assertThat(response.getBody()).contains("жанр1");
            assertThat(response.getBody()).contains("жанр2");
            assertThat(response.getBody()).contains("жанр3");
            assertThat(response.getBody()).isEqualTo("{\"filmList\":[{\"id\":0,\"title\":\"фильм1\",\"releaseDate\":null,\"color\":null,\"genre\":\"жанр1\",\"language\":null,\"country\":null,\"rating\":null,\"leadActor\":null,\"directorName\":null,\"leadActorFBLikes\":0,\"castFBLikes\":0,\"directorFBLikes\":0,\"movieFBLikes\":0,\"imdbScore\":0.0,\"totalReview\":0,\"duration\":0,\"revenue\":0,\"budget\":0},{\"id\":0,\"title\":\"фильм2\",\"releaseDate\":null,\"color\":null,\"genre\":\"жанр2\",\"language\":null,\"country\":null,\"rating\":null,\"leadActor\":null,\"directorName\":null,\"leadActorFBLikes\":0,\"castFBLikes\":0,\"directorFBLikes\":0,\"movieFBLikes\":0,\"imdbScore\":0.0,\"totalReview\":0,\"duration\":0,\"revenue\":0,\"budget\":0},{\"id\":0,\"title\":\"фильм3\",\"releaseDate\":null,\"color\":null,\"genre\":\"жанр3\",\"language\":null,\"country\":null,\"rating\":null,\"leadActor\":null,\"directorName\":null,\"leadActorFBLikes\":0,\"castFBLikes\":0,\"directorFBLikes\":0,\"movieFBLikes\":0,\"imdbScore\":0.0,\"totalReview\":0,\"duration\":0,\"revenue\":0,\"budget\":0}]}");
        }
 }

