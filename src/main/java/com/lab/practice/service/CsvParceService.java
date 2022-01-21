package com.lab.practice.service;

import com.lab.practice.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CsvParceService {

    @Autowired
    StorageService fileStorageService;

    public List<Film> parseCsvFile(String filename) {
        try {
            return Files.lines(fileStorageService.load(filename))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(this::creatingFilm)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    private Film creatingFilm(String[] array) {
        Film film = new Film();
        film.setTitle(array[0]);
        film.setReleaseDate(array[1]);
        film.setColor(array[2]);
        film.setGenre(array[3]);
        film.setLanguage(array[4]);
        film.setCountry(array[5]);
        film.setRating(array[6]);
        film.setLeadActor(array[7]);
        film.setDirectorName(array[8]);
        film.setLeadActorFBLikes(checkIntData(array[9]));
        film.setCastFBLikes(checkIntData(array[10]));
        film.setDirectorFBLikes(checkIntData(array[11]));
        film.setMovieFBLikes(checkIntData(array[12]));
        film.setImdbScore(checkDoubleData(array[13].replace(",",".")));
        film.setTotalReview(checkIntData(array[14]));
        film.setDuration(checkIntData(array[15]));
        film.setRevenue(checkLongData(array[16]));
        film.setBudget(checkLongData(array[17]));
        return film;
    }

    private int checkIntData(String inputValue){
        if (inputValue.isEmpty()){
            return 0;
        }
        return Integer.parseInt(inputValue);
    }
    private double checkDoubleData(String inputValue){
        if (inputValue.isEmpty()){
            return 0;
        }
        return Double.parseDouble(inputValue);
    }

    private long checkLongData(String inputValue){
        if (inputValue.isEmpty()){
            return 0;
        }
        return Long.parseLong(inputValue);
    }

}