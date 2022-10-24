package com.example.PlayList.reposirory;

import com.example.PlayList.model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends CrudRepository<Music, Long> {
}
