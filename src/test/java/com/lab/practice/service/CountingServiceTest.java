package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.when;


/* public class CountingServiceTest {

    @InjectMocks
    private CountingService countingService;
    @Mock
    private CsvParceService csvParceService;


    private String filename = "test.csv";
    @Mock
    private Film film1;
    @Mock
    private Film film2;
    @Mock
    private Film film3;

    @Test
    public void maxValueTest() {
        when(csvParceService.parseCsvFile(filename)).thenReturn(List.of(film1,film2,film3));
        when(film1.getBudget()).thenReturn(100L);
        when(film2.getBudget()).thenReturn(200L);
        when(film3.getBudget()).thenReturn(300L);

        long maxValue = countingService.maxValue(filename);
        Assert.assertEquals(300l, maxValue);
    }

    @Test
    public void sumTest(){
        when(csvParceService.parseCsvFile(filename)).thenReturn(List.of(film1,film2,film3));
        when(film1.getBudget()).thenReturn(100L);
        when(film2.getBudget()).thenReturn(200L);
        when(film3.getBudget()).thenReturn(300L);

        long sum = countingService.sum(filename);
        Assert.assertEquals(600L, sum);
    }

}*/
