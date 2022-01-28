package com.backbase.technical.challenge.controller;

import com.backbase.technical.challenge.dto.MovieDto;
import com.backbase.technical.challenge.model.Movie;
import com.backbase.technical.challenge.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    final MovieService movieService;

    @GetMapping("/winner")
    public ResponseEntity<List<Movie>> findWinnerMovies() {
        return new ResponseEntity<>(movieService.findWinnerMovies(), HttpStatus.OK);
    }

    @PostMapping("/top-movie")
    public ResponseEntity<List<Movie>> findTopMovies(@Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.findTopTenMovies(movieDto), HttpStatus.OK);
    }
}
