package com.lab.practice.filters;

import com.lab.practice.entity.Film;

@FunctionalInterface
public interface ColumnMapper {
    long apply(Film film);
}
