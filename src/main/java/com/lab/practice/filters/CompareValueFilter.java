package com.lab.practice.filters;

import com.lab.practice.entity.Film;

// Это отдельный класс если не прокатит вариант с Long.parse()

@FunctionalInterface
public interface CompareValueFilter {
    boolean filter(Film film, long compareValue);
}
