package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Song {

    private final String name;
    private final String artist;
    private List<String> tags = new ArrayList<String>();

    /**
     * Song Constructor
     * 
     * @param name   The name of the song
     * @param artist The song's artist
     * @param temp   Determines whether the song object is only a temporary
     *               instantiation and therefore doesn't need to be saved to the csv
     * @throws IllegalArgumentException
     */
    public Song(String name, String artist, boolean temp) throws IllegalArgumentException {
        super();
        if (name == "" || artist == "") {
            throw new IllegalArgumentException("Missing name or artist");
        }
        this.name = name;
        this.artist = artist;

        // Only save the song if it is NOT a temporary song object
        if (!temp) {
            saveSong();
        }
    }

    /**
     * Get the name of a song
     * 
     * @return The song name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the song's artist
     * 
     * @return The song's artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Add a tag to the song. Deletes the song from the csv first, adds the tag, then creates a new entry in the CSV.
     * 
     * @param tag The tag to add to the song
     */
    public void addTag(String tag) {
        if(this.containsTag(tag)){
            System.out.println("This song already has the tag '" + tag + "'");
        }else{
            this.deleteSong();
            this.tags.add(tag);
            this.saveSong();
        }
    }

    /**
     * Remove a tag from the song. Similar process to addTag, but first checks whether the tag exists.
     * 
     * @param tag The tag to remove from the song
     */
    public void removeTag(String tag) {
        if (containsTag(tag)) {
            this.deleteSong();
            tags.remove(tag);
            this.saveSong();
        }
    }

    /**
     * Determine whether a song is tagged with a specified tag
     * 
     * @return a boolean that represents whether the tag belongs to the song
     */
    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

    /**
     * Save the song to the songs csv file.
     */
    public void saveSong() {
        try {
            FileWriter writer = new FileWriter("src/main/java/com/charliebignell/app/songs.csv", true);
            writer.append("\n" + this.getEntry());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the song from the csv file
     */
    public void deleteSong() {
        String line = ""; // Represents the line of the csv currently being read
        List<String> songsToKeep = new ArrayList<String>(); // Holds the songs to keep when looping through the csv
        
        // Go through the songs csv, and make a note of all songs to keep i.e. all songs except this one
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/com/charliebignell/app/songs.csv"));

            // Add all song entries to songsToKeep, unless it's this song
            while ((line = br.readLine()) != null) {
                if (!line.equals(this.getEntry())) {
                    songsToKeep.add(line);
                }
            }
            songsToKeep.remove(0);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Re-add all the songs to keep
        try {
            FileWriter writer = new FileWriter("src/main/java/com/charliebignell/app/songs.csv");
            writer.append("name,artist,tags");

            for (String s : songsToKeep) {
                writer.append("\n" + s);
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the csv row that represents the song
     * 
     * @return The csv row as a string
     */
    public String getEntry() {
        StringBuffer tagsList = new StringBuffer();

        // If there are no tags, set the '-' placeholder
        if (tags.isEmpty()) {
            tagsList.append("-");

        // Add the tags in the correct format
        } else {
            for (int i = 0; i < tags.size() - 1; i++) {
                tagsList.append(tags.get(i) + "-");
            }
            tagsList.append(tags.get(tags.size() - 1));
        }

        return this.name + "," + this.artist + "," + tagsList;
    }

    /**
     * 
     * An override of the object's toString() method
     * 
     * @return The formatted string representation of the song
     */
    @Override
    public String toString() {
        return this.name + ", by " + this.artist;
    }

}
