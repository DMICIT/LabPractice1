package com.lab.practice.service;

import com.lab.practice.data.CountingData;
import com.lab.practice.entity.Film;
import com.lab.practice.filters.ColumnMapper;
import com.lab.practice.filters.EFilterValues;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class CountingService {

    @Autowired
    CsvParceService csvParceService;

    Map<String, ColumnMapper> mapColumnName = new HashMap<>();

    {
        mapColumnName.put("leadActor", film -> Long.parseLong(film.getLeadActor()));
        mapColumnName.put("budget", Film::getBudget);
        mapColumnName.put("castFb", Film::getCastFBLikes);
        mapColumnName.put("directorFb", Film::getDirectorFBLikes);
        mapColumnName.put("movieFb", Film::getMovieFBLikes);
        mapColumnName.put("imdb", film -> (long) film.getImdbScore());
        mapColumnName.put("totalReview", Film::getTotalReview);
        mapColumnName.put("duration", Film::getDuration);
        mapColumnName.put("revenue", Film::getRevenue);
    }

    public long maxValue(CountingData countingData){
        if(StringUtils.isNotEmpty(countingData.getFilterKey()) && StringUtils.isNotEmpty(countingData.getValue())){

            return maxValue(countingData.getFileName(), countingData.getColumnName(),
                    countingData.getFilterKey(), countingData.getValue());
        }
        return maxValue(countingData.getFileName(), countingData.getColumnName());
    }

    private long maxValue(String fileName, String columnName) {
        List<Film> films = csvParceService.parseCsvFile(fileName);
        ColumnMapper columnMapper = mapColumnName.get(columnName);

        if (columnMapper == null) {
            throw new IllegalArgumentException("Not valid column");
        }
        return films.stream()
                .mapToLong(columnMapper::apply)
                .max()
                .orElse(0);
    }

    private long maxValue(String fileName, String columnName, String filterKey, String value) {

        List<Film> films = csvParceService.parseCsvFile(fileName);
        ColumnMapper columnMapper = mapColumnName.get(columnName);
        EFilterValues filterValue = Stream.of(EFilterValues.values())
                .filter(eFilterValue -> eFilterValue.matchKey(filterKey))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not valid column"));

        if (columnMapper == null) {
            throw new IllegalArgumentException("Not valid column");
        }
        return films.stream()
                .filter(film -> filterValue.filter(film, value))
                .mapToLong(columnMapper::apply)
                .max()
                .orElse(0);
    }

    public long sum(CountingData countingData){
        if(StringUtils.isNotEmpty(countingData.getFilterKey()) && StringUtils.isNotEmpty(countingData.getValue())){

            return sum(countingData.getFileName(), countingData.getColumnName(),
                    countingData.getFilterKey(), countingData.getValue());
        }
        return sum(countingData.getFileName(), countingData.getColumnName());
    }

    private long sum(String fileName, String columnName) {
        List<Film> films = csvParceService.parseCsvFile(fileName);
        ColumnMapper columnMapper = mapColumnName.get(columnName);
        if (columnMapper == null) {
            throw new IllegalArgumentException("Not valid column");
        }
        return films.stream()
                .mapToLong(columnMapper::apply)
                .sum();
    }

    private long sum(String fileName, String columnName, String filterKey, String value) {
        ColumnMapper columnMapper = mapColumnName.get(columnName);
        EFilterValues filterValue = Stream.of(EFilterValues.values())
                .filter(eFilterValues -> eFilterValues.matchKey(filterKey))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not valid column"));
        if (columnMapper == null) {
            throw new IllegalArgumentException("Not valid column");
        }
        List<Film> films = csvParceService.parseCsvFile(fileName);
        return films.stream().filter(film -> filterValue.filter(film, value))
                .mapToLong(columnMapper::apply)
                .sum();
    }


}
