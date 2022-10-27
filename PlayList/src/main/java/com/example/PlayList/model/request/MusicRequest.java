package com.example.PlayList.model.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MusicRequest {
    String artistName;
    String trackName;
    int releaseDate;
    String genre;
    int len;
    String topic;
}
