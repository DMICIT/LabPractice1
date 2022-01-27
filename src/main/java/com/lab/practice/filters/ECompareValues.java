package com.lab.practice.filters;

import com.lab.practice.entity.Film;

// Это отдельный класс если не прокатит вариант с Long.parse()

public enum ECompareValues {

    IMDBHigher("imdbHigher", (film, compareInput) -> compareInput < film.getImdbScore()),
    IMDBLower("imdbLower", (film, compareInput) -> compareInput > film.getImdbScore()),
    BudgetHigher("budgetHigher", (film, compareInput) -> compareInput < film.getBudget()),
    BudgetLower("budgetLower", (film, compareInput) -> compareInput > film.getBudget()),
    RevenueHigher( "revenueHigher", (film, compareInput) -> compareInput > film.getRevenue()),
    RevenueLower( "revenueLower", (film, compareInput) -> compareInput < film.getRevenue()),
    DurationHigher("durationHigher", (film, compareInput) -> compareInput > film.getDuration()),
    DurationLower("durationLower", (film, compareInput) -> compareInput < film.getDuration()),
    TotalReviewHigher("totalReviewHigher", (film, compareInput) -> compareInput > film.getTotalReview()),
    TotalReviewLower("totalReviewLower", (film, compareInput) -> compareInput > film.getTotalReview());


    private String key;
    private CompareValueFilter compareValueFilter;

    ECompareValues(String key, CompareValueFilter compareValueFilter) {
        this.key = key;
        this.compareValueFilter = compareValueFilter;
    }

    public boolean filter(Film film, long compareInput){
        return this.compareValueFilter.filter(film,compareInput);
    }
    public boolean matchKey(String input){
        return this.key.equals(input);
    }

}
