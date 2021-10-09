package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Playlist<T> implements Tracklist<T> {

    private List<String> tags = new ArrayList<String>();

    public Playlist() {
        super();
    }

    public void populate(T songRef) {
        if (songRef instanceof String) {
            tags.add((String) songRef);
        } else {
            throw new IllegalArgumentException("Input must be a String");
        }
    }

    public void dePopulate(T songRef) {
        if (songRef instanceof String) {
            tags.remove(songRef);
        } else {
            throw new IllegalArgumentException("Input must be a String");
        }
    }

    public void playSongs() {

        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("D:/Projects/Tagger/src/main/java/com/charliebignell/app/songs.csv"));

            while ((line = br.readLine()) != null)
            {
                String[] songDetails = line.split(",");
                String[] songTags = songDetails[2].split("-");

                for(String t : songTags){
                    if(tags.contains(t)){
                        Song s = new Song(songDetails[0], songDetails[1], true);
                        System.out.println(s.toString());
                        break;
                    }
                }
                
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
