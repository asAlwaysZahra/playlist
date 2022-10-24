package com.example.PlayList.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Table
@Entity
@Getter
@Setter

public class PlayList {

    public PlayList()
    {
        playlist = new LinkedList();
    }

    @Id
    private long id;
    private LinkedList playlist;
    private int size;
}
