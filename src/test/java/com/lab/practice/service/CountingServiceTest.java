package com.lab.practice.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.nio.file.Path;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountingServiceTest {

    @Autowired
    CountingService countingService;
    @MockBean
    private FileStorageService storageService;

    private final String fileName = "serviceTestFile.csv";

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