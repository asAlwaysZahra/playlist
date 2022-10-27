package com.example.PlayList.service;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.PlayList;
import com.example.PlayList.model.response.MusicResponse;
import com.example.PlayList.reposirory.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public long generateId() {
        List<Music> musicList = (List<Music>) musicRepository.findAll();
        return musicList.size() + 1;
    }

    public MusicResponse createMusic(Music music) {
        music.setId(generateId());
        return musicRepository.save(music).response();
    }

    public List<MusicResponse> saveAll(List<Music> musicList) {
        for (Music music : musicList)
            createMusic(music);

        return musicList.stream().map(Music::response).collect(Collectors.toList());
    }

    public MusicResponse getMusicById(long id) {
        return musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found")).response();
    }

    public MusicResponse updateMusic(long id, Music music) {
        Music m = musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));

        m.setLen(music.getLen());
        m.setTrackName(music.getTrackName());
        m.setArtistName(music.getArtistName());
        m.setGenre(music.getGenre());
        m.setTopic(music.getTopic());
        m.setReleaseDate(music.getReleaseDate());

        return musicRepository.save(m).response();
    }

    public void deleteMusic(long id) {
        musicRepository.deleteById(id);
    }

    public List<MusicResponse> getAllMusic() {
        return ((List<Music>) musicRepository.findAll()).stream().map(Music::response).collect(Collectors.toList());
    }

    // filter ---------------------------------------------------------------------
    public List<MusicResponse> getMusicsByArtist(String artist) {
        return musicRepository.findAllByArtistNameContaining(artist).stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByTrackName(String title) {
        return musicRepository.findAllByTrackNameContaining(title).stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByReleaseDate(int releaseDate) {
        return musicRepository.findAllByReleaseDate(releaseDate).stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByGenre(String genre) {
        return musicRepository.findAllByGenreContaining(genre).stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByLen(int len) {
        return musicRepository.findAllByLen(len).stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByTopic(String topic) {
        return musicRepository.findAllByTopicContaining(topic).stream().map(Music::response).collect(Collectors.toList());
    }

    // sort -----------------------------------------------------------------------
    public List<MusicResponse> getMusicsByArtistAsc() {
        return musicRepository.findByOrderByArtistNameAsc().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByArtistDesc() {
        return musicRepository.findByOrderByArtistNameDesc().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByTrackNameAsc() {
        return musicRepository.findByOrderByTrackNameAsc().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByTrackNameDesc() {
        return musicRepository.findByOrderByTrackNameDesc().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByReleaseDateAsc() {
        return musicRepository.findByOrderByReleaseDateAsc().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> getMusicsByReleaseDateDesc() {
        return musicRepository.findByOrderByReleaseDateDesc().stream().map(Music::response).collect(Collectors.toList());
    }
}
