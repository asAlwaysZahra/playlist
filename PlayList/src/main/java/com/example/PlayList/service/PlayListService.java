package com.example.PlayList.service;

import com.example.PlayList.model.LinkedList;
import com.example.PlayList.model.Music;
import com.example.PlayList.model.Node;
import com.example.PlayList.model.PlayList;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PlayListService {

    public PlayList clearPlayList(PlayList playList) {
        LinkedList newPlayList = new LinkedList();
        playList.setPlaylist(newPlayList);
        playList.setSize(0);

        return playList;
    }

    public PlayList mergedPlayList(int id, PlayList playList1, PlayList playList2) {

        PlayList mergedPlayList = new PlayList();
        mergedPlayList.setId(id);

        mergedPlayList.getPlaylist().getHeader().setNext(playList1.getPlaylist().getHeader().getNext());
        playList1.getPlaylist().getHeader().getNext().setPrevious(mergedPlayList.getPlaylist().getHeader());

        playList1.getPlaylist().getTrailer().getPrevious().setNext(playList2.getPlaylist().getHeader().getNext());
        playList2.getPlaylist().getHeader().getNext().setPrevious(playList1.getPlaylist().getTrailer().getPrevious());

        mergedPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());
        playList2.getPlaylist().getTrailer().getPrevious().setNext(mergedPlayList.getPlaylist().getTrailer());

        mergedPlayList.getPlaylist().getTrailer().setPrevious(playList2.getPlaylist().getTrailer().getPrevious());
        mergedPlayList.getPlaylist().setSize(playList1.getPlaylist().getSize() + playList2.getPlaylist().getSize());

        return mergedPlayList;
    }

    public PlayList shufflePlayList(PlayList playList) {

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

        playList = clearPlayList(playList);

        for (Music music : temp) {
            playList.getPlaylist().addLast(music);
        }

        return playList;
    }

    public PlayList shuffleMergePlayList(int id, PlayList playList1, PlayList playList2) {

        PlayList shuffleMergePlayList = mergedPlayList(id, playList1, playList2);
        shuffleMergePlayList = shufflePlayList(shuffleMergePlayList);

        return playList1;
    }

    static void arrayShuffle(Music[] arr)
    {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = rand.nextInt(arr.length);
            Music g = arr[index];
            arr[index] = arr[i];
            arr[i] = g;
        }
    }
}
