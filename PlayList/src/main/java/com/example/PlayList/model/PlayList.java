package com.example.PlayList.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@Table
@Entity
public class PlayList {

    @Id
    private long id;
    @Transient
    private LinkedList playlist;
    private int size;

    public PlayList() {
        playlist = new LinkedList();
    }
}
