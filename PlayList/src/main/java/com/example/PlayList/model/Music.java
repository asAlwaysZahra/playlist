package com.example.PlayList.model;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
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

