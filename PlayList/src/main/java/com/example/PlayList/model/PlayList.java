package com.example.PlayList.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Table
@Entity
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Transient
    private LinkedList playlist;
    private int size;
    
    @Column(unique=true)
    private String name;

    @Column(unique = true)
    private String name;

    public PlayList() {
        playlist = new LinkedList();
    }

    public List<Music> musicsList() {

        List<Music> musics = new ArrayList<>();
        Node t = playlist.getHeader().getNext();
        while (t != playlist.getTrailer()) {
            musics.add(t.getData());
        }

        return musics;
    }

    public void clearPlayList() {

        LinkedList newPlayList = new LinkedList();
        this.setPlaylist(newPlayList);
        this.setSize(0);
    }

}
