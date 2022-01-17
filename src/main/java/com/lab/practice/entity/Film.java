package com.lab.practice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    String title;
    String releaseDate;
    String color;
    String genre;
    String language;
    String country;
    String rating;
    String leadActor;
    String directorName;
    int leadActorFBLikes;
    int castFBLikes;
    int directorFBLikes;
    int movieFBLikes;
    double imdbScore;
    int totalReview;
    int duration;
    long revenue;
    long budget;


}
