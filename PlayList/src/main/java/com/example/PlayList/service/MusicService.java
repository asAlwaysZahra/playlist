package com.example.PlayList.service;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.PlayList;
import com.example.PlayList.reposirory.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public long generateId() {
        List<Music> musicList = (List<Music>) musicRepository.findAll();
        return musicList.size() + 1;
    }

    public Music createMusic(Music music) {
        music.setId(generateId());
        return musicRepository.save(music);
    }

    public List<Music> saveAll(List<Music> musicList) {
        for (Music music : musicList)
            createMusic(music);

        return musicList;
    }

    public Music getMusicById(long id) {
        return musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public Music updateMusic(long id, Music music) {
        Music m = musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));

        m.setLen(music.getLen());
        m.setTrackName(music.getTrackName());
        m.setArtistName(music.getArtistName());
        m.setGenre(music.getGenre());
        m.setTopic(music.getTopic());
        m.setReleaseDate(music.getReleaseDate());

        return musicRepository.save(m);
    }

    public void deleteMusic(long id) {
        musicRepository.deleteById(id);
    }

    public List<Music> getAllMusic() {
        return (List<Music>) musicRepository.findAll();
    }

    // filter ---------------------------------------------------------------------
    public List<Music> getMusicsByArtist(String artist) {
        return musicRepository.findAllByArtistNameContaining(artist);
    }

    public List<Music> getMusicsByTrackName(String title) {
        return musicRepository.findAllByTrackNameContaining(title);
    }

    public List<Music> getMusicsByReleaseDate(int releaseDate) {
        return musicRepository.findAllByReleaseDate(releaseDate);
    }

    public List<Music> getMusicsByGenre(String genre) {
        return musicRepository.findAllByGenreContaining(genre);
    }

    public List<Music> getMusicsByLen(int len) {
        return musicRepository.findAllByLen(len);
    }

    public List<Music> getMusicsByTopic(String topic) {
        return musicRepository.findAllByTopicContaining(topic);
    }

    // sort -----------------------------------------------------------------------
    public List<Music> getMusicsByArtistAsc() {
        return musicRepository.findByOrderByArtistNameAsc();
    }

    public List<Music> getMusicsByArtistDesc() {
        return musicRepository.findByOrderByArtistNameDesc();
    }

    public List<Music> getMusicsByTrackNameAsc() {
        return musicRepository.findByOrderByTrackNameAsc();
    }

    public List<Music> getMusicsByTrackNameDesc() {
        return musicRepository.findByOrderByTrackNameDesc();
    }

    public List<Music> getMusicsByReleaseDateAsc() {
        return musicRepository.findByOrderByReleaseDateAsc();
    }

    public List<Music> getMusicsByReleaseDateDesc() {
        return musicRepository.findByOrderByReleaseDateDesc();
    }
}
