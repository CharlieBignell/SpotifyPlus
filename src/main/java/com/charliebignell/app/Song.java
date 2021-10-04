package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;

public class Song {

    private final String name;
    private final String artist;
    private List<String> tags = new ArrayList<String>();

    public Song(String name, String artist) throws IllegalArgumentException {
        super();
        if (name == null || artist == null) {
            throw new IllegalArgumentException("Missing name or artist");
        }
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    @Override
    public String toString() {
        return this.name + ", by " + this.artist;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag){
        if(containsTag(tag)){
            tags.remove(tag);
        }
    }

    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

}
