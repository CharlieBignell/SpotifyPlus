package com.charliebignell.app;

import java.util.Set;
import java.util.HashSet;

public class Song extends Audio {

    private final String name;
    private final String artist;
    private Set<String> tags = new HashSet<String>();

    /**
     * Song Constructor
     * 
     * @param name   The name of the song
     * @param artist The song's artist

     * @throws IllegalArgumentException
     */
    public Song(String name, String artist) throws IllegalArgumentException {
        super(name, artist);
        if (name == "" || artist == "") {
            throw new IllegalArgumentException("Missing name or artist");
        }
        this.name = name;
        this.artist = artist;
    }

    /**
     * Add a tag(string) to the song. Deletes the song from the csv first, adds the tag, then creates a new entry in the CSV.
     * 
     * @param tag The tag to add to the song
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Add a tag (integer) to the song. Deletes the song from the csv first, adds the tag, then creates a new entry in the CSV.
     * 
     * @param tag The tag to add to the song
     */
    public void addTag(int tag) {      
        this.tags.add(Integer.toString(tag));
    }

    /**
     * Remove a tag from the song. Similar process to addTag, but first checks whether the tag exists.
     * 
     * @param tag The tag to remove from the song
     */
    public void removeTag(String tag) {
        if (containsTag(tag)) {
            tags.remove(tag);
        }
    }

    /**
     * Remove a tag (int) from the song. Similar process to addTag, but first checks whether the tag exists.
     * 
     * @param tag The tag to remove from the song
     */
    public void removeTag(int tag) {
        String stringTag = Integer.toString(tag);

        if (containsTag(stringTag)) {
            tags.remove(stringTag);
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
