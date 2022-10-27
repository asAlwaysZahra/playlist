package com.example.PlayList.controller;

import com.example.PlayList.file.CSVFile;
import com.example.PlayList.model.Music;
import com.example.PlayList.model.request.MusicRequest;
import com.example.PlayList.model.response.MusicResponse;
import com.example.PlayList.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/new")
    public ResponseEntity<MusicResponse> createMusic(@RequestBody MusicRequest musicRequest) {
        return new ResponseEntity<>(musicService.createMusic(musicRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicResponse> getMusicById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(musicService.getMusicById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MusicResponse>> getAllMusic() {
        return new ResponseEntity<>(musicService.getAllMusic(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MusicResponse> updateMusic(@PathVariable long id, @RequestBody MusicRequest musicRequest) {
        return new ResponseEntity<>(musicService.updateMusic(id, musicRequest), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable long id) {
        musicService.deleteMusic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/readCSV")
    public ResponseEntity<List<MusicResponse>> readCSV() {
        List<MusicRequest> musicList = CSVFile.readCSV("src/main/resources/musics.csv");
        return new ResponseEntity<>(musicService.saveAll(musicList), HttpStatus.CREATED);
    }

    // filter ---------------------------------------------------------------------
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<MusicResponse>> getMusicByArtist(@PathVariable String artist) {
        return new ResponseEntity<>(musicService.getMusicsByArtist(artist), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<MusicResponse>> getMusicByName(@PathVariable String name) {
        return new ResponseEntity<>(musicService.getMusicsByTrackName(name), HttpStatus.OK);
    }

    @GetMapping("/release/{year}")
    public ResponseEntity<List<MusicResponse>> getMusicByReleaseDate(@PathVariable int year) {
        return new ResponseEntity<>(musicService.getMusicsByReleaseDate(year), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MusicResponse>> getMusicByGenre(@PathVariable String genre) {
        return new ResponseEntity<>(musicService.getMusicsByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/len/{len}")
    public ResponseEntity<List<MusicResponse>> getMusicByLen(@PathVariable int len) {
        return new ResponseEntity<>(musicService.getMusicsByLen(len), HttpStatus.OK);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<MusicResponse>> getMusicByTopic(@PathVariable String topic) {
        return new ResponseEntity<>(musicService.getMusicsByTopic(topic), HttpStatus.OK);
    }

    // sort -----------------------------------------------------------------------
    @GetMapping("/sort/artist/{order}")
    public ResponseEntity<List<MusicResponse>> sortByArtistAsc(@PathVariable String order) {
        if (order.equals("asc"))
            return new ResponseEntity<>(musicService.getMusicsByArtistAsc(), HttpStatus.OK);
        else
            return new ResponseEntity<>(musicService.getMusicsByArtistDesc(), HttpStatus.OK);
    }

    @GetMapping("/sort/track/{order}")
    public ResponseEntity<List<MusicResponse>> sortByTrackNameAsc(@PathVariable String order) {
        if (order.equals("asc"))
            return new ResponseEntity<>(musicService.getMusicsByTrackNameAsc(), HttpStatus.OK);
        else
            return new ResponseEntity<>(musicService.getMusicsByTrackNameDesc(), HttpStatus.OK);
    }

    @GetMapping("/sort/release/{order}")
    public ResponseEntity<List<MusicResponse>> sortByReleaseDateAsc(@PathVariable String order) {
        if (order.equals("asc"))
            return new ResponseEntity<>(musicService.getMusicsByReleaseDateAsc(), HttpStatus.OK);
        else
            return new ResponseEntity<>(musicService.getMusicsByReleaseDateDesc(), HttpStatus.OK);
    }
}
