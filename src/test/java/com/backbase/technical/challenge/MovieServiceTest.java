package com.backbase.technical.challenge;

import com.backbase.technical.challenge.model.Movie;
import com.backbase.technical.challenge.model.MovieRepository;
import com.backbase.technical.challenge.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MovieServiceTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    private static final String MOVIE_ENDPOINT = "/movie/winner";
    private static final String TOP_MOVIE_ENDPOINT = "/movie/top-movie";

    @Test
    void test_find_winner_movies() throws Exception {
        //************************
        //          Given
        //************************
        //************************
        //          WHEN
        //************************
        MvcResult responseBody = mockMvc.perform(MockMvcRequestBuilders.get(MOVIE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        // check rest response
        assertThat(responseBody.getResponse().getContentAsString()).isNotEmpty();
        assertThat(responseBody.getResponse().getContentAsString()).contains("True");
    }

    @Test
    void test_find_Top_ten_movies_1() throws Exception {
        //************************
        //          Given
        //************************
        String requestBody  = "{\"title\":\"The Sunset\",\"rate\":\"7\",\"year\":\"2010\"," +
                "\"genre\":\"Drama,Thriller\",\"director\":\"DarrenAronofsky\"," +
                "\"writer\":\"MarkHeyman,AndresHeinz,JohnJ.McLaughlin\"," +
                "\"actors\":\"NataliePortman,MilaKunis,VincentCassel\"," +
                "\"country\":\"UnitedStates\",\"awards\":\"Won1Oscar.97wins&279nominationstotal\"}";

        movieRepository.deleteAll();

        saveMovies("$515004880", "2000", "The Sunset");
        saveMovies("$115004880", "2003", "The Sunrise");
        saveMovies("$895004880", "2007", "The Midnight");
        saveMovies("$915004880", "2009", "The Hunger Game 1");
        saveMovies("$315004880", "2010", "The Hunger Game 2");
        saveMovies("$115004880", "2011", "The Hunger Game 3");
        saveMovies("$15004880", "2013", "Toy Story 1");
        saveMovies("$905004880", "2015", "Toy Story 2");
        saveMovies("$615004880", "2018", "Toy Story 3");
        saveMovies("$415004880", "2020", "Me Before You");

        //************************
        //          WHEN
        //************************

        MvcResult responseBody = mockMvc.perform(MockMvcRequestBuilders.post(TOP_MOVIE_ENDPOINT)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //************************
        //          THEN
        //************************
        // check rest response
        assertThat(responseBody.getResponse().getContentAsString()).isNotEmpty();
        assertThat(responseBody.getResponse().getContentAsString()).contains("Me Before You");
    }

    private void saveMovies(String boxOffice, String year, String title) {
        movieRepository.save(Movie.builder()
                .boxOffice(boxOffice)
                .year(year)
                .country("UnitedStates")
                .title(title).build());
    }
}
