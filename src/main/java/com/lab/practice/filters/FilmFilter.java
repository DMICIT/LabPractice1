package com.lab.practice.filters;

import com.lab.practice.entity.Film;

import java.util.List;

@FunctionalInterface
public interface FilmFilter {
    long apply(Film film);

}
