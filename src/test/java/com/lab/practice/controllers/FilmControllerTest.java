package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.service.CsvParceService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmControllerTest {
    @InjectMocks
    private FilmController filmController;

    @Mock
    private CsvParceService csvParceService;

    private String fileName = " test.csv";
    @Mock
    private Film film1;
    @Mock
    private Film film2;
    @Mock
    private Film film3;


    @Test
    void viewFilmListTest() {

        when(csvParceService.parseCsvFile(fileName)).thenReturn(List.of(film1,film2,film3));
        List<Film> result = filmController.viewFilmList(fileName);
        Assert.assertEquals(List.of(film1,film2,film3), result);

    }
}