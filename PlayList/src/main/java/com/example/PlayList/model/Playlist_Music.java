package com.example.PlayList.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table
@Entity
public class Playlist_Music {

    @Id
    @GeneratedValue
    private long id;
    private long playlist_id;
    private long music_id;

    public Playlist_Music(long playlist_id, long music_id) {
        this.playlist_id = playlist_id;
        this.music_id = music_id;
    }
}
