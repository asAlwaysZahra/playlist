package com.example.PlayList.service;

import com.example.PlayList.model.PlayList;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    private PlayList mergedPlayList(int id, PlayList playList1, PlayList playList2) {

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
}
