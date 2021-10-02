package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;

public class Song 
{

    private String name = null;
    private String artist = null;
    private List<String> tags = new ArrayList<String>();
    

    public Song(String name, String artist){
        super();
        this.name = name;
        this.artist = artist;
    }

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }

    public String getFormattedSong(){
        return this.name + ", by " + this.artist;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }    

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public void getTags(){
        
    }
}
