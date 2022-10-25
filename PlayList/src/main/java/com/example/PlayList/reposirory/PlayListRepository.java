package com.example.PlayList.reposirory;

import com.example.PlayList.model.PlayList;
import org.springframework.data.repository.CrudRepository;

public interface PlayListRepository extends CrudRepository<PlayList, Long> {
    PlayList findByName(String name);
}
