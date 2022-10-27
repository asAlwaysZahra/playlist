package com.example.PlayList.file;

import com.example.PlayList.model.Music;
import com.example.PlayList.model.request.MusicRequest;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CSVFile {

    static public ArrayList<MusicRequest> readCSV(String fileName) {

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<com.example.PlayList.file.Music> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(com.example.PlayList.file.Music.class).build().parse();

            ArrayList<MusicRequest> musics = new ArrayList<>();

            int i = 0;
            for (com.example.PlayList.file.Music t : beans) {
                if (i == 0) {
                    i++;
                    continue;
                }
                musics.add(MusicRequest.builder()
                        .artistName(t.getArtistName())
                        .trackName(t.getTrackName())
                        .releaseDate(Integer.parseInt(t.getReleaseDate()))
                        .genre(t.getGenre())
                        .len(Integer.parseInt(t.getLen()))
                        .topic(t.getTopic())
                        .build());
            }

            return musics;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
