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

    @ManyToMany
    @JoinTable(
            name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> musics;

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
