package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountingService {

    @Autowired
    CsvParceService csvParceService;

    public long maxValue(String fileName){
        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films.stream()
                .mapToLong(Film::getBudget)
                .max()
                .orElse(0);
    }

    public long sum (String filename){
        List<Film> films = csvParceService.parseCsvFile(filename);
        return films.stream()
                .mapToLong(Film::getBudget)
                .sum();
    }
}
