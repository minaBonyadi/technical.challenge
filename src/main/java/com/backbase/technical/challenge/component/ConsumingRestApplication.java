package com.backbase.technical.challenge.component;

import com.backbase.technical.challenge.model.MovieRepository;
import com.backbase.technical.challenge.dto.MoviesInfoDto;
import com.backbase.technical.challenge.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class ConsumingRestApplication {

    final MovieRepository movieRepository;

    public void saveImdbMoviesInfo(List<MoviesInfoDto> moviesInfos) {
        RestTemplate restTemplate = new RestTemplate();
        List<Movie> movies = new ArrayList<>();

        moviesInfos.forEach(movie -> {

            String url = String.format("https://www.omdbapi.com/?t=%s&apikey=fc3d4a73", movie.getName().replace(' ','+'));
            try {
                Movie movieInfo = restTemplate.getForObject(url, Movie.class);
                if (movieInfo != null && movieInfo.getTitle() != null) {
                    movies.add(movieInfo);
                }
                log.info(String.format("getting information of movie {%s}", movie.getName()));
            } catch (Exception exception) {
                log.info(String.format("happening exception in getting information of movie title {%s}, url {%s}", movie.getName(), url), exception);
            }

        });

        movieRepository.saveAll(movies);
    }
}
