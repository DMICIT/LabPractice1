package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountingServiceTest {

    @InjectMocks
    private CountingService countingService;

    private CsvParceService csvParceService;
    @Mock
    private FileStorageService storageService;

    private final String fileName = "serviceTestFile.csv";

    @BeforeEach
    void setUp() {
        csvParceService = new CsvParceService();
        csvParceService.fileStorageService = storageService;
        countingService.csvParceService = csvParceService;
    }

    @Test
    void maxValue() throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(fileName)).thenReturn(path);
        long result = countingService.maxValue(fileName, "budget");

        Assertions.assertEquals(6000000, result);

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