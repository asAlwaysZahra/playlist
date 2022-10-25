package com.example.PlayList.service;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.Node;
import com.example.PlayList.model.PlayList;
import com.example.PlayList.reposirory.MusicRepository;
import com.example.PlayList.reposirory.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private MusicRepository musicRepository;

    public PlayList createPlayList(PlayList playList) {
        return playListRepository.save(playList);
    }

    public PlayList getPlayListById(long id) {
        return playListRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayList not found"));
    }

    public PlayList updatePlayList(PlayList playList) {
        return playListRepository.save(playList);
    }

    public void deletePlayList(long id) {
        playListRepository.deleteById(id);
    }

    public List<PlayList> getAllPlayList() {
        return (List<PlayList>) playListRepository.findAll();
    }

    public PlayList getPlayListByName(String name) {
        return playListRepository.findByName(name);
    }

    // methods ---------------------------------------------------------------------

    public PlayList addMusicToPlayList(long playListId, long musicId) {
        PlayList playList = playListRepository.findById(playListId).orElseThrow(() -> new RuntimeException("PlayList not found"));
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));
        playList.getPlaylist().addLast(music);
        playList.setSize(playList.getSize() + 1);
        return playList;
    }

    public PlayList removeMusicFromPlayList(long playListId, long musicId) {
        PlayList playList = playListRepository.findById(playListId).orElseThrow(() -> new RuntimeException("PlayList not found"));
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));
//        playList.getPlaylist().remove(music); todo
        playList.setSize(playList.getSize() - 1);
        return playList;
    }

    private void arrayShuffle(Music[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = rand.nextInt(arr.length);
            Music g = arr[index];
            arr[index] = arr[i];
            arr[i] = g;
        }
    }

    public List<Music> mergedPlayList(PlayList playList1, PlayList playList2) {

        PlayList newPlayList = new PlayList();

        newPlayList.getPlaylist().getHeader().setNext(playList1.getPlaylist().getHeader().getNext());
        playList1.getPlaylist().getHeader().getNext().setPrevious(newPlayList.getPlaylist().getHeader());

        playList1.getPlaylist().getTrailer().getPrevious().setNext(playList2.getPlaylist().getHeader().getNext());
        playList2.getPlaylist().getHeader().getNext().setPrevious(playList1.getPlaylist().getTrailer().getPrevious());

        newPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());
        playList2.getPlaylist().getTrailer().getPrevious().setNext(newPlayList.getPlaylist().getTrailer());

        newPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());
        newPlayList.getPlaylist().setSize(playList1.getPlaylist().getSize() + playList2.getPlaylist().getSize());

        return newPlayList.musicsList().stream().distinct().toList();

    }

    public List<Music> shufflePlayList(PlayList playList) {

        PlayList shufflePlayList = new PlayList();
        shufflePlayList.setId(playList.getId());

        Music[] temp = new Music[playList.getSize()];
        int i = 0;
        Node current = playList.getPlaylist().getHeader().getNext();
        while (current != playList.getPlaylist().getTrailer()) {
            temp[i] = current.getData();
            i++;
            current = current.getNext();
        }

        arrayShuffle(temp);

        playList.clearPlayList();

        for (Music music : temp) {
            playList.getPlaylist().addLast(music);
        }

        return playList.musicsList();
    }

    public List<Music> shuffleMergePlayList(PlayList playList1, PlayList playList2) {

        List<Music> shuffleMergePlayList = mergedPlayList(playList1, playList2);
        Collections.shuffle(shuffleMergePlayList);

        return shuffleMergePlayList;
    }
}
