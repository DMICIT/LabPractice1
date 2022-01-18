package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public long sum (String fileName){
        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films.stream()
                .mapToLong(Film::getBudget)
                .sum();
    }

    public List<Film> filterFilmByBudget (String fileName, long value ){
        List<Film> films = csvParceService.parseCsvFile(fileName);
       return films.stream()
                .filter((Film film) -> film.getBudget() > value)
                .sorted(Comparator.comparing(Film::getTitle))
                .collect(Collectors.toList());
    }
}
