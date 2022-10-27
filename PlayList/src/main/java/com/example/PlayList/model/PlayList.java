package com.example.PlayList.model;

import com.example.PlayList.model.request.PlayListRequest;
import com.example.PlayList.model.response.PlayListResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
@Builder
public class PlayList {

    @Id
    @Column(name = "playlist_id")
    private long id;
    @Transient
    private LinkedList playlist;
    private int size;

    @Column(unique = true)
    private String name;

    public List<Music> musicsList() {

        List<Music> musics = new ArrayList<>();
        Node t = playlist.getHeader().getNext();
        while (t != playlist.getTrailer()) {
            musics.add(t.getData());
            t = t.getNext();
        }

        return musics;
    }

    public void removeMusic(Music music) {
        Node t = playlist.getHeader().getNext();
        while (t != playlist.getTrailer()) {
            if (t.getData().getId() == music.getId())
                playlist.remove(t);

            t = t.getNext();
        }
    }

    public void clearPlayList() {

        LinkedList newPlayList = new LinkedList();
        this.setPlaylist(newPlayList);
        this.setSize(0);
    }

    public PlayListResponse response() {
        return PlayListResponse.builder()
                .id(id)
                .name(name)
                .size(size)
                .musics(musicsList())
                .build();
    }

    public PlayListResponse createResponse() {
        return PlayListResponse.builder()
                .id(id)
                .name(name)
                .size(size)
                .build();
    }

    public PlayListRequest request() {
        return PlayListRequest.builder()
                .name(name)
                .size(size).
                build();
    }
}
