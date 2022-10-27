package com.example.PlayList.model.response;

import com.example.PlayList.model.Music;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class PlayListResponse {
    long id;
    String name;
    int size;
    List<Music> musics;
}
