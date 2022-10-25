package com.example.PlayList.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Table
@Entity
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Transient
    private LinkedList playlist;
    private int size;

    public PlayList() {
        playlist = new LinkedList();
    }
}
