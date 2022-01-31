package com.lab.practice.controllers;

import com.lab.practice.data.CountingData;
import com.lab.practice.service.CountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountingController {
    @Autowired
    CountingService countingService;

    @GetMapping(value = "/maxValue")
    public long getMaxValue(@ModelAttribute CountingData countingData){
       return countingService.maxValue(countingData);
    }

    @GetMapping(value = "/sum")
    public long getSum(@ModelAttribute CountingData countingData){
        return countingService.sum(countingData);
    }
}
