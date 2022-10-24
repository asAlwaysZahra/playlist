package com.example.PlayList.file;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

@Getter
public class Music {

    @CsvBindByPosition(position = 0)
    private String artistName;

    @CsvBindByPosition(position = 1)
    private String trackName;

    @CsvBindByPosition(position = 2)
    private String releaseDate;

    @CsvBindByPosition(position = 3)
    private String genre;

    @CsvBindByPosition(position = 4)
    private String len;

    @CsvBindByPosition(position = 5)
    private String topic;

}
