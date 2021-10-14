package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Playlist<T> implements Tracklist<T> {

    private List<String> tags = new ArrayList<String>();

    /*
     * Playlist Constructor
     */
    public Playlist() {
        super();
    }

    /**
     * Add a tag to a playlist
     * 
     * @param songRef The song reference. In the case of a playlist, this is a tag
     *                in the form of a string
     * @throws IllegalArgumentException
     */
    public void populate(T songRef) {
        if (songRef instanceof String) {
            tags.add((String) songRef);
        } else {
            throw new IllegalArgumentException("Input must be a String");
        }
    }

    /**
     * Remove a tag from a playlist
     * 
     * @param songRef The song reference. In the case of a playlist, this is a tag
     *                in the form of a string
     * @throws IllegalArgumentException
     */
    public void dePopulate(T songRef) {
        if (songRef instanceof String) {
            tags.remove(songRef);
        } else {
            throw new IllegalArgumentException("Input must be a String");
        }
    }

    /**
     * Play the songs in the playlist, for demonstrative purposes it simply ouputs
     * the songs to the console
     */
    public void playSongs() {

        String line = "";

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/com/charliebignell/app/songs.csv"));

            while ((line = br.readLine()) != null) {
                String[] songDetails = line.split(",");
                String[] songTags = songDetails[2].split("-");

                for (String t : songTags) {
                    // If the song contains the tag, create a temporary Song object and output the
                    // formatted string
                    if (tags.contains(t)) {
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
