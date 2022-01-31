package com.lab.practice.filters;

import com.lab.practice.entity.Film;


public enum EFilterValues {

    // сортировка по String
    Title("title",(film, input ) -> input.equals(film.getTitle())),
    Color("color", (film, input ) -> input.equals(film.getColor())),
    Genre("genre", (film, input) -> input.equals(film.getGenre())),
    Language("language", (film, input) -> input.equals(film.getLanguage())),
    Country("country", (film, input) -> input.equals(film.getCountry())),
    LeadActor("leadActor",(film, input) -> input.equals(film.getLeadActor())),

    // сортировка по Long
    IMDBHigher("imdbHigher", (film, input) -> Long.parseLong(input) < film.getImdbScore()),
    IMDBLower("imdbLower", (film, input) -> Long.parseLong(input) > film.getImdbScore()),
    BudgetHigher("budgetHigher", (film, input) -> Long.parseLong(input) < film.getBudget()),
    BudgetLower("budgetLower", (film, input) -> Long.parseLong(input) > film.getBudget()),
    RevenueHigher( "revenueHigher", (film, input) -> Long.parseLong(input) > film.getRevenue()),
    RevenueLower( "revenueLower", (film, input) -> Long.parseLong(input) < film.getRevenue()),
    DurationHigher("durationHigher", (film, input) -> Long.parseLong(input) > film.getDuration()),
    DurationLower("durationLower", (film, input) -> Long.parseLong(input) < film.getDuration()),
    TotalReviewHigher("totalReviewHigher", (film, input) -> Long.parseLong(input) > film.getTotalReview()),
    TotalReviewLower("totalReviewLower", (film, input) -> Long.parseLong(input) < film.getTotalReview());

    private String key;
    private FilmFilter filmFilter;

    EFilterValues(String key, FilmFilter filmFilter) {
        this.key = key;
        this.filmFilter = filmFilter;
    }

   public boolean filter(Film film, String input){
        return this.filmFilter.filter(film, input);
    }

   public boolean matchKey(String input){
        return this.key.equals(input);
    }
}
