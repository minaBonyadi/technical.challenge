package com.backbase.technical.challenge.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MoviesInfoDto {
    String year;
    String rank;
    String category;
    String name;
    String additionalInfo;
}
