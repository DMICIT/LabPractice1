package com.lab.practice.service;

import com.lab.practice.entity.Film;
import com.lab.practice.filters.ColumnMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountingServiceTest {

    @Autowired
    CountingService countingService;
    @InjectMocks
    private CsvParceService csvParceService;
    @Mock
    private FileStorageService storageService;

     ColumnMapper columnMapper = Film::getBudget;

    private final String fileName = "serviceTestFile.csv";

    @Test
    void maxValue() throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);

        List<Film> films = csvParceService.parseCsvFile(fileName);
      //  when(columnMapper::apply).thenReturn(Film::getBudget);

        long result = countingService.maxValue(fileName, "budget");
      //  System.out.println(result);

      //  Assertions.assertEquals(6000000, result);

    }

    @Test
    void sum() throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);
        long result = countingService.sum(fileName, "budget");

        Assertions.assertEquals(11027000, result);

    }
}