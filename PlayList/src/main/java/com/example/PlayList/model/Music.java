package com.example.PlayList.model;

import com.example.PlayList.model.response.MusicResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
public class Music {

    @Id
    @Column(name = "music_id")
    private long id;

    private String artistName;
    private String trackName;
    private int releaseDate;
    private String genre;
    private int len;
    private String topic;

    public MusicResponse response() {
        return MusicResponse.builder()
                .artistName(artistName)
                .trackName(trackName)
                .releaseDate(releaseDate)
                .genre(genre)
                .len(len)
                .topic(topic)
                .build();
    }
}

