package com.backbase.technical.challenge;


import com.backbase.technical.challenge.component.Reader;
import com.backbase.technical.challenge.dto.MoviesInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ConsumingRestApplicationTest {

    @Autowired
    private Reader reader;

    @Test
    void test_csv_file_parser() {
        //************************
        //          Given
        //************************
        //************************
        //          WHEN
        //************************
        List<MoviesInfoDto> moviesInfoDtos = reader.parseCsvFile();

        //************************
        //          THEN
        //************************
        assertThat(moviesInfoDtos.contains("year"));
        assertThat(moviesInfoDtos.contains("category"));
        assertThat(moviesInfoDtos.contains("name"));
        assertThat(moviesInfoDtos.contains("additionalInfo"));
        assertThat(moviesInfoDtos.size()).isEqualTo(10137);
    }
}
