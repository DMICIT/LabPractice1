package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.core.io.ClassPathResource;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CsvParceServiceTest {

    @InjectMocks
    private CsvParceService csvParceService;

    @Mock
    private FileStorageService storageService;

    @Test
    public void parseCsvFileTest() throws IOException {

        String fileName = "serviceTestFile.csv";
        ClassPathResource resource = new ClassPathResource(fileName, getClass());
        Path path = resource.getFile().toPath();

        when(storageService.load(fileName)).thenReturn(path);

        List<Film> result = csvParceService.parseCsvFile(fileName);
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals("Over the Hill to the Poorhouse", result.get(0).getTitle());

    }
}