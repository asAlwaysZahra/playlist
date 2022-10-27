package com.example.PlayList.model.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MusicResponse {
    long id;
    String artistName;
    String trackName;
    int releaseDate;
    String genre;
    int len;
    String topic;
}
