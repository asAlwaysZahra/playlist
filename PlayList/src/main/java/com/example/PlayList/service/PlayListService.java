package com.example.PlayList.service;

import com.example.PlayList.model.*;
import com.example.PlayList.service.*;
import com.example.PlayList.model.response.PlayListResponse;
import com.example.PlayList.reposirory.MusicRepository;
import com.example.PlayList.reposirory.PlayListRepository;
import com.example.PlayList.reposirory.Playlist_MusicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private Playlist_MusicRepo playlist_musicRepo;

    public long generateId() {
        List<PlayList> playLists = (List<PlayList>) playListRepository.findAll();
        return playLists.size() + 1;
    }

    public List<Music> getMusics(long id) {
        return playlist_musicRepo.getMusics(id);
    }

    public PlayListResponse createPlayList(PlayListRequest playListRequest) {
        PlayList playList = PlayList.builder()
                .id(generateId())
                .name(playListRequest.getName())
                .size(playListRequest.getSize())
                .build();
        return playListRepository.save(playList).response();
    }

    public PlayListResponse getPlayListById(long id) {
        PlayList playList = playListRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayList not found"));

        playList.setPlaylist(new LinkedList());
        List<Music> musicList = playlist_musicRepo.getMusics(id);
        playList.setSize(musicList.size());

        for (Music music : musicList)
            playList.getPlaylist().addLast(music);

        return playList.response();
    }

    private PlayList getPlayListWithMusics(long id) {
        PlayList playList = playListRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayList not found"));

        playList.setPlaylist(new LinkedList());
        List<Music> musicList = playlist_musicRepo.getMusics(id);
        playList.setSize(musicList.size());

        for (Music music : musicList)
            playList.getPlaylist().addLast(music);

        return playList;
    }

    public PlayListResponse updatePlayList(long id, PlayListRequest playListRequest) {
        PlayList playList = playListRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayList not found"));

        if(playListRequest.getName() != null) playList.setName(playListRequest.getName());

        return playListRepository.save(playList).response();
    }

    public void deletePlayList(long id) {
        playListRepository.deleteById(id);
    }

    public List<PlayListResponse> getAllPlayList() {
        List<PlayList> playLists = (List<PlayList>) playListRepository.findAll();
        return playLists.stream().map(PlayList::response).collect(Collectors.toList());
    }

    public PlayListResponse getPlayListByName(String name) {
        return playListRepository.findByName(name).response();
    }

    // methods ---------------------------------------------------------------------

    public PlayListResponse addMusicToPlayList(long playListId, long musicId) {
        // getPlayListById(playListId)  todo  ? playListRepository.findById(playListId).orElseThrow(() -> new RuntimeException("PlayList not found"));
        PlayList playList = getPlayListWithMusics(playListId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));

        Playlist_Music playlist_music = new Playlist_Music(playListId, musicId);
        playlist_musicRepo.save(playlist_music);

        playList.getPlaylist().addLast(music);

        playList.setSize(playList.getSize() + 1);
        playListRepository.save(playList);

        return playList.response();
    }

    public PlayListResponse removeMusicFromPlayList(long playListId, long musicId) {

        PlayList playList = getPlayListWithMusics(playListId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));

        playlist_musicRepo.removePM(playListId, musicId);
        playList.removeMusic(music);

        playList.setSize(playList.getSize() - 1);
        updatePlayList(playListId, playList.request());

        return playList.response();
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

    public List<Music> mergedPlayList(long playListId1, long playListId2, String newName) {

        PlayList playList1 = getPlayListWithMusics(playListId1);
        PlayList playList2 = getPlayListWithMusics(playListId2);

        PlayList newPlayList = new PlayList();
        newPlayList.setPlaylist(new LinkedList());
        newPlayList.setName(newName);
        newPlayList.setId(generateId());

        newPlayList.getPlaylist().getHeader().setNext(playList1.getPlaylist().getHeader().getNext());
        playList1.getPlaylist().getHeader().getNext().setPrevious(newPlayList.getPlaylist().getHeader());

        playList1.getPlaylist().getTrailer().getPrevious().setNext(playList2.getPlaylist().getHeader().getNext());
        playList2.getPlaylist().getHeader().getNext().setPrevious(playList1.getPlaylist().getTrailer().getPrevious());

        newPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());
        playList2.getPlaylist().getTrailer().getPrevious().setNext(newPlayList.getPlaylist().getTrailer());

        newPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());

        newPlayList.getPlaylist().setSize(playList1.getPlaylist().getSize() + playList2.getPlaylist().getSize());
        newPlayList.setSize(newPlayList.getPlaylist().getSize());

        Node t1 = newPlayList.getPlaylist().getHeader().getNext();

        while (t1 != newPlayList.getPlaylist().getTrailer()) {
            Node t2 = t1.getNext();
            while (t2 != newPlayList.getPlaylist().getTrailer()) {
                if (t1.getData().getId() == t2.getData().getId()) {
                    newPlayList.getPlaylist().remove(t2);
                }
                t2 = t2.getNext();
            }
            t1 = t1.getNext();
        }

        playListRepository.save(newPlayList);

        for (Music music : newPlayList.musicsList())
            playlist_musicRepo.save(new Playlist_Music(newPlayList.getId(), music.getId()));

        return newPlayList.musicsList();

    }

    public List<MusicResponse> shufflePlayList(long playListId) {

        PlayList playList = getPlayListWithMusics(playListId); //playListRepository.findById(playListId).orElseThrow(() -> new RuntimeException("PlayList not found"));

        Music[] temp = new Music[playList.getPlaylist().size()];
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

        return playList.musicsList().stream().map(Music::response).collect(Collectors.toList());
    }

    public List<MusicResponse> shuffleMergePlayList(long playListId1, long playListId2, String newName) {

        List<Music> shuffleMergePlayList = mergedPlayList(playListId1, playListId2, newName);
        Collections.shuffle(shuffleMergePlayList);

        return shuffleMergePlayList.stream().map(Music::response).collect(Collectors.toList());
    }

    public PlayListResponse getLikedMusics() {
        Optional<PlayList> playList = playListRepository.findById(0L);
        if (playList.isEmpty())
            return playListRepository.save(new PlayList(0L, new LinkedList(), 0, "Liked Songs")).response();
        else
            return getPlayListWithMusics(0L).response();
    }

    public PlayListResponse likeMusic(long musicId) {
        return addMusicToPlayList(0L, musicId);
    }

    public PlayListResponse dislikeMusic(long musicId) {
        return removeMusicFromPlayList(0L, musicId);
    }
}
