package com.example.PlayList.controller;

import com.example.PlayList.model.PlayList;
import com.example.PlayList.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @PostMapping("/new")
    public ResponseEntity<PlayList> createPlayList(@RequestBody PlayList playList) {
        try {
            return new ResponseEntity<>(playListService.createPlayList(playList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            // todo logger
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(playListService.getPlayListById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayList>> getAllPlayList() {
        return new ResponseEntity<>(playListService.getAllPlayList(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlayList> updatePlayList(@RequestBody PlayList playList) {
        return new ResponseEntity<>(playListService.updatePlayList(playList), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deletePlayList(@PathVariable long id) {
        playListService.deletePlayList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlayList> getPlayListByName(@PathVariable String name) {
        return new ResponseEntity<>(playListService.getPlayListByName(name), HttpStatus.OK);
    }

    // methods ---------------------------------------------------------------------

    @PostMapping("/add/{id}/{musicId}")
    public ResponseEntity<PlayList> addMusicToPlayList(@PathVariable long id, @PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.addMusicToPlayList(id, musicId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}/{musicId}")
    public ResponseEntity<PlayList> removeMusicFromPlayList(@PathVariable long id, @PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.removeMusicFromPlayList(id, musicId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shuffle/{id}")
    public ResponseEntity<PlayList> shufflePlayList(@PathVariable long id) {
        return null; // todo
    }

}
