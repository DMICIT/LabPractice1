package com.lab.practice.controllers;

import com.lab.practice.entity.Film;
import com.lab.practice.entity.FilmList;
import com.lab.practice.service.CsvParceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilmController {

    @Autowired
    CsvParceService csvParceService;

    @GetMapping(value = "/films")
    public ResponseEntity<FilmList> viewFilmList(@RequestParam String fileName){
        List<Film> films = csvParceService.parseCsvFile(fileName);
        FilmList listFilm = new FilmList(films);
        return ResponseEntity.ok().body(listFilm);
    }

}
