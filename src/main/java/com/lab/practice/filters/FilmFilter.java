package com.lab.practice.filters;

import com.lab.practice.entity.Film;

@FunctionalInterface
public interface FilmFilter {
    boolean filter(Film film, String param);
}
