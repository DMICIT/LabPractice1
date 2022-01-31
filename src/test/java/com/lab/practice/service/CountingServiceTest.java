package com.lab.practice.service;


import com.lab.practice.data.CountingData;
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

    private final CountingData countingData = new CountingData("serviceTestFile.csv","budget");
    private final CountingData countingData2 = new CountingData("serviceTestFile.csv","budget", "totalReviewHigher", "1000000");

    @Test
    void maxValue() throws IOException {

        ClassPathResource resource = new ClassPathResource(countingData.getFileName(), getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(countingData.getFileName())).thenReturn(path);

        long result = countingService.maxValue(countingData);

        Assertions.assertEquals(6000000, result);
    }

    @Test
    void maxValueWithFilters() throws IOException {

        ClassPathResource resource = new ClassPathResource(countingData.getFileName(), getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(countingData.getFileName())).thenReturn(path);

        long result = countingService.maxValue(countingData2);

        Assertions.assertEquals(6000000, result);

    }



    @Test
    void sum() throws IOException {

        ClassPathResource resource = new ClassPathResource(countingData.getFileName(), getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(countingData.getFileName())).thenReturn(path);

        long result = countingService.sum(countingData);

        Assertions.assertEquals(11027000, result);

    }

    @Test
    void sumWithFilters() throws IOException {

        ClassPathResource resource = new ClassPathResource(countingData.getFileName(), getClass());
        Path path = resource.getFile().toPath();
        when(storageService.load(countingData.getFileName())).thenReturn(path);

        long result = countingService.sum(countingData2);

        Assertions.assertEquals(11027000, result);
    }
}