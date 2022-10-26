package com.example.PlayList.reposirory;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.Playlist_Music;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Playlist_MusicRepo extends CrudRepository<Playlist_Music, Long> {


    @Transactional
    @Modifying
    @Query("delete from Playlist_Music pm where pm.playlist_id = :pid and pm.music_id = :mid")
    void removePM(@Param("pid") long playlist_id, @Param("mid") long music_id);

    @Query("select m from Music m " +
            "inner join Playlist_Music pm on m.id = pm.music_id " +
            "inner join PlayList p on pm.playlist_id = p.id " +
            "where p.id = :pid")
    List<Music> getMusics(@Param("pid") long id);

}
