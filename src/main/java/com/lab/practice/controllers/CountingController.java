package com.lab.practice.controllers;

import com.lab.practice.service.CountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountingController {
    @Autowired
    CountingService countingService;

    @GetMapping(value = "/maxValue")
    public long getMaxValue(@RequestParam String fileName ,
                            @RequestParam String columnName){
       return countingService.maxValue(fileName, columnName);
    }

    @GetMapping(value = "/sum")
    public long getSum(@RequestParam String fileName,
                       @RequestParam String columnName){
        return countingService.sum(fileName, columnName);
    }
}
