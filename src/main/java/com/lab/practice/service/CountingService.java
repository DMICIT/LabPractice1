package com.lab.practice.service;

import com.lab.practice.entity.Film;
import com.lab.practice.filters.FilmFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountingService {

    @Autowired
    CsvParceService csvParceService;

    Map<String, FilmFilter> mapColumnName = new HashMap<>();
    {
        mapColumnName.put("leadActor", film -> Long.parseLong(film.getLeadActor()));
        mapColumnName.put("budget", film -> film.getBudget());
        mapColumnName.put("castFb", film -> film.getCastFBLikes());
        mapColumnName.put("directorFb", film -> film.getDirectorFBLikes());
        mapColumnName.put("movieFb", film -> film.getMovieFBLikes());
        mapColumnName.put("imdb", film -> (long) film.getImdbScore());
        mapColumnName.put("totalReview", film -> film.getTotalReview());
        mapColumnName.put("duration", film -> film.getDuration());
        mapColumnName.put("revenue", film -> film.getRevenue());
    }

    public long maxValue(String fileName, String columnName){
        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films.stream()
                .mapToLong(film -> mapColumnName.get(columnName).apply(film))
                .max()
                .orElse(0);
    }

    public long sum (String fileName, String columnName){
        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films.stream()
                .mapToLong(film -> mapColumnName.get(columnName).apply(film))
                .sum();
    }

//    public List<Film> filterFilm (String fileName, String filterName, String value ){
//        List<Film> films = csvParceService.parseCsvFile(fileName);
//       return films.stream()
//                .filter(film -> map1.get(filterName).apply(film, value))
//                .sorted(Comparator.comparing(Film::getTitle))
//                .collect(Collectors.toList());
//    }
}
