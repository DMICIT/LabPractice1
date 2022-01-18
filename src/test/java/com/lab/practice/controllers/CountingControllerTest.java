package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.service.CountingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CountingControllerTest {

    @InjectMocks
    private CountingController countingController;

    @Mock
    private CountingService countingService;

    private String fileName = " test.csv";


    @Test
    void getMaxValue() {

        when(countingService.maxValue(fileName)).thenReturn(100L);

        long result = countingController.getMaxValue(fileName);

        Assert.assertEquals(100L, result);

    }

    @Test
    void getSum() {
        when(countingService.sum(fileName)).thenReturn(200L);
        long sum = countingController.getSum(fileName);

        Assert.assertEquals(200L, sum);
    }

}