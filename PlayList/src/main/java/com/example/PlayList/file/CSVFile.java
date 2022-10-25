package com.example.PlayList.file;

import com.example.PlayList.model.Music;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CSVFile {

    static public ArrayList<Music> readCSV(String fileName) {

//        String fileName = "/media/influx/Programming/University/Fall01/DS/Codes/Projects/project-2-asAlwaysZahra/musics.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<com.example.PlayList.file.Music> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(com.example.PlayList.file.Music.class).build().parse();

            ArrayList<Music> musics = new ArrayList<>();

            int i = 0;
            for (com.example.PlayList.file.Music t : beans) {
                if (i == 0) {
                    i++;
                    continue;
                }
                musics.add(new Music(i++, t.getArtistName(), t.getTrackName(), Integer.parseInt(t.getReleaseDate()), t.getGenre(), Integer.parseInt(t.getLen()), t.getTopic()));
            }

            return musics;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
