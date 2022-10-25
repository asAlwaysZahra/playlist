package com.example.PlayList.reposirory;

import com.example.PlayList.model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends CrudRepository<Music, Long> {

    // find by fields -------------------------------------------------------------
    Music findByArtistName(String artist);

    Music findByTrackName(String name);

    // filter ---------------------------------------------------------------------
    List<Music> findAllByArtistNameContaining(String artist);

    List<Music> findAllByTrackNameContaining(String name);

    List<Music> findAllByGenreContaining(String genre);

    List<Music> findAllByTopicContaining(String topic);

    List<Music> findAllByReleaseDate(int year); // todo date between

    List<Music> findAllByLen(int len);

    // sort -----------------------------------------------------------------------
    List<Music> findByOrderByArtistNameAsc(); // todo find all ?

    List<Music> findByOrderByArtistNameDesc();

    List<Music> findByOrderByTrackNameAsc();

    List<Music> findByOrderByTrackNameDesc();

    List<Music> findByOrderByReleaseDateAsc();

    List<Music> findByOrderByReleaseDateDesc();
}
