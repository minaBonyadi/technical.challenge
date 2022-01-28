package com.backbase.technical.challenge.component;

import com.backbase.technical.challenge.dto.MoviesInfoDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Reader {

    public List<MoviesInfoDto> parseCsvFile() {
        try{
            File file = ResourceUtils.getFile("classpath:academy_awards.csv");
            CSVReader reader = new CSVReaderBuilder(new FileReader(file)).
                            withSkipLines(1). // Skipping the first line as it is a header
                            build();
            return reader.readAll().stream().map(data->
                        MoviesInfoDto.builder()
                                .year(data[0])
                                .category(data[1])
                                .name(data[2])
                                .additionalInfo(data[3])
                                .build()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
