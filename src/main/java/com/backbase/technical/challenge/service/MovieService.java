package com.backbase.technical.challenge.service;

import com.backbase.technical.challenge.component.ConsumingRestApplication;
import com.backbase.technical.challenge.model.MovieRepository;
import com.backbase.technical.challenge.component.Reader;
import com.backbase.technical.challenge.dto.MovieDto;
import com.backbase.technical.challenge.dto.MoviesInfoDto;
import com.backbase.technical.challenge.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MovieService {

    final Reader reader;
    final MovieRepository movieRepository;
    final ConsumingRestApplication consumingRestApplication;

    @PostConstruct
    @Transactional
    public void validate() {
        movieRepository.deleteInvalidData("N/A");
    }

    public List<Movie> findWinnerMovies() {

        List<MoviesInfoDto> winningMovieInfos = reader.parseCsvFile().stream()
                .filter(moviesInfo -> moviesInfo.getCategory().equals("Best Picture"))
                .collect(Collectors.toList());

        consumingRestApplication.saveImdbMoviesInfo(winningMovieInfos);

        return movieRepository.findAll().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Transactional
    public List<Movie> findTopTenMovies(MovieDto updatedMovies) {

        Movie updatedMovie = movieRepository.findByTitle(updatedMovies.getTitle().trim())
                .orElseThrow(() -> new RuntimeException(String.format("Movie with title [%s] not found", updatedMovies.getTitle())));

        validate();
        updatedMovie.setRate(String.valueOf(updatedMovies.getRate()));

        return sortMovies(movieRepository.findAll());
    }


    private List<Movie> sortMovies(List<Movie> movies) {
        return movies.stream().sorted((m1, m2) -> {
            try {
                double m1BoxOffice = Double.parseDouble(m1.getBoxOffice().substring(1).replace(",", ""));
                double m2BoxOffice = Double.parseDouble(m2.getBoxOffice().substring(1).replace(",", ""));
                return Double.compare(m2BoxOffice, m1BoxOffice);
            } catch (NullPointerException ex) {
                    log.info(String.format("This exception happened in sorting movies {%s}", ex));
            }
            return 0;
        }).limit(10).collect(Collectors.toList());
    }
}
