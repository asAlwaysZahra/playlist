package com.example.PlayList.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Music {

    @Id
    private long id;

    private String artistName;
    private String trackName;
    private int releaseDate;
    private String genre;
    private int len;
    private String topic;

}

