package com.backbase.technical.challenge.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MovieDto {
    @NotNull(message = "Title should filled")
    String title;
    @Size(min = 1, message = "Minimum is 1")
    @Size(max = 10, message = "Maximum is 10")
    int rate;
    String year;
    String genre;
    String director;
    String writer;
    String actors;
    String country;
    String awards;
}
