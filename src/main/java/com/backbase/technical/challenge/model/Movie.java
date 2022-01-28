package com.backbase.technical.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonProperty("Title")
    @Column(length = 200, nullable = false)
    String title;
    @JsonProperty("Rate")
    String  rate;
    @JsonProperty("Year")
    String year;
    @JsonProperty("Released")
    String released;
    @JsonProperty("Runtime")
    String runtime;
    @JsonProperty("Genre")
    String genre;
    @JsonProperty("Director")
    String director;
    @JsonProperty("Writer")
    String writer;
    @JsonProperty("Actors")
    String actors;
    @JsonProperty("Plot")
    String plot;
    @JsonProperty("Language")
    String language;
    @JsonProperty("Country")
    String country;
    @JsonProperty("Awards")
    String awards;
    @JsonProperty("Poster")
    String poster;
    @JsonProperty("MetaScore")
    int metaScore;
    @JsonProperty("ImdbRating")
    String imdbRating;
    @JsonProperty("ImdbVotes")
    String imdbVotes;
    @JsonProperty("ImdbID")
    String imdbID;
    @JsonProperty("Type")
    String type;
    @JsonProperty("DVD")
    String dvd;
    @JsonProperty("BoxOffice")
    String boxOffice;
    @JsonProperty("Production")
    String production;
    @JsonProperty("Website")
    String website;
    @JsonProperty("Response")
    String response;
}
