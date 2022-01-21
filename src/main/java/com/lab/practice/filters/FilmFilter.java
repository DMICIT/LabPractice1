package com.lab.practice.filters;

import com.lab.practice.entity.Film;

@FunctionalInterface
public interface FilmFilter {
    long apply(Film film);

}
