package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.repository.FilmRepository;
import com.lab.practice.service.CsvParceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    CsvParceService csvParceService;

    @GetMapping(value = "/films")
    public List<Film> viewFilmList(@RequestParam String fileName){

        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films;
    }
}
