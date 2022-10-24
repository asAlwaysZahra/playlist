package com.example.PlayList.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Music {

    @Id
    private long id;

}
