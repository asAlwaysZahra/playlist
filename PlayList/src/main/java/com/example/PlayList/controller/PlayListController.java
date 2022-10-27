package com.example.PlayList.controller;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.request.PlayListRequest;
import com.example.PlayList.model.*;
import com.example.PlayList.model.response.PlayListResponse;
import com.example.PlayList.service.PlayListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/playlists")
public class PlayListController {

    private PlayListService playListService;

    @PostMapping("/new")
    public ResponseEntity<PlayListResponse> createPlayList(@RequestBody PlayListRequest playListRequest) {
        try {
            return new ResponseEntity<>(playListService.createPlayList(playListRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            // todo logger
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayListResponse> getPlayListById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(playListService.getPlayListById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayListResponse>> getAllPlayList() {
        return new ResponseEntity<>(playListService.getAllPlayList(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlayListResponse> updatePlayList(@PathVariable long id, @RequestBody PlayListRequest playListRequest) {
        return new ResponseEntity<>(playListService.updatePlayList(id, playListRequest), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deletePlayList(@PathVariable long id) {
        playListService.deletePlayList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlayListResponse> getPlayListByName(@PathVariable String name) {
        return new ResponseEntity<>(playListService.getPlayListByName(name), HttpStatus.OK);
    }

    @GetMapping("/{id}/musics")
    public ResponseEntity<List<Music>> getMusics(@PathVariable long id) {
        return new ResponseEntity<>(playListService.getMusics(id), HttpStatus.OK);
    }

    // methods ---------------------------------------------------------------------

    @PostMapping("/add/{id}/{musicId}")
    public ResponseEntity<PlayListResponse> addMusicToPlayList(@PathVariable long id, @PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.addMusicToPlayList(id, musicId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}/{musicId}")
    public ResponseEntity<PlayListResponse> removeMusicFromPlayList(@PathVariable long id, @PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.removeMusicFromPlayList(id, musicId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/shuffle/{id}")
    public ResponseEntity<List<MusicResponse>> shufflePlayList(@PathVariable long id) {
        try {
            return new ResponseEntity<>(playListService.shufflePlayList(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/merge/{id1}/{id2}/{name}")
    public ResponseEntity<List<Music>> mergePlayList(@PathVariable long id1, @PathVariable long id2, @PathVariable String name) {
        try {
            return new ResponseEntity<>(playListService.mergedPlayList(id1, id2, name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/merge/shuffle/{id1}/{id2}/{name}")
    public ResponseEntity<List<MusicResponse>> mergeShufflePlayList(@PathVariable long id1, @PathVariable long id2, @PathVariable String name) {
        try {
            return new ResponseEntity<>(playListService.shuffleMergePlayList(id1, id2, name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/like/{musicId}")
    public ResponseEntity<PlayListResponse> like(@PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.likeMusic(musicId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dislike/{musicId}")
    public ResponseEntity<PlayListResponse> dislike(@PathVariable long musicId) {
        try {
            return new ResponseEntity<>(playListService.dislikeMusic(musicId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/likedSongs")
    public ResponseEntity<PlayListResponse> likedMusics() {
        try {
            return new ResponseEntity<>(playListService.getLikedMusics(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
