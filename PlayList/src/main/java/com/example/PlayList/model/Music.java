package com.example.PlayList.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

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
    private String trackName;
    private String artistName;
    private Date releaseDate;
    private String genre;
    private String topic;
    private int len;

}

