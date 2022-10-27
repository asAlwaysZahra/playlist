package com.example.PlayList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
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

    public void removeMusic(Music music)
    {
        Node t = playlist.getHeader().getNext();
        while(t != playlist.getTrailer())
        {
            if(t.getData().getId() == music.getId())
                playlist.remove(t);

            t = t.getNext();
        }
    }
    public void clearPlayList() {

        LinkedList newPlayList = new LinkedList();
        this.setPlaylist(newPlayList);
        this.setSize(0);
    }

}
