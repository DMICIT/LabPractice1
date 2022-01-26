package com.lab.practice.filters;

import com.lab.practice.entity.Film;

public enum EFilterValues {
    Title("title",(film, input ) -> input.equals(film.getTitle())),
    Color("color", (film, input ) -> input.equals(film.getColor())),
    Genre("genre", (film, input) -> input.equals(film.getGenre())),
    Language("language", (film, input) -> input.equals(film.getLanguage())),
    Country("country", (film, input) -> input.equals(film.getCountry())),
    LeadActor("leadActor",(film, input) -> input.equals(film.getLeadActor())),
    DirectorName("directorName",(film,input) ->input.equals(film.getDirectorName()));

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
