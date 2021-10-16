package com.charliebignell.app;

import java.util.HashSet;
import java.util.Set;

public class Playlist<T> implements Tracklist<T> {

    private Set<String> tags = new HashSet<String>();

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
        } else if (songRef instanceof Integer) {
            tags.add(Integer.toString((Integer) songRef));
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
        } else if (songRef instanceof Integer) {
            tags.remove(Integer.toString((Integer) songRef));
        } else {
            throw new IllegalArgumentException("Input must be a String");
        }
    }

    /**
     * Play the songs in the playlist, for demonstrative purposes it simply ouputs
     * the songs to the console
     */
    public void playSongs(Library lib) {
        System.out.println();
        for (String tag : tags) {
            lib.playSongsFromTag(tag);
        }
    }

}
