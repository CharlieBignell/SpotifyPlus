package com.charliebignell.app;

import java.util.LinkedList;
import java.util.List;

public class Album<T> implements Tracklist <T> {
    
    private final String name;
    private List<Song> songs = new LinkedList<Song>();

    public Album(String name){
        super();
        this.name = name;
    }

    public void populate(T songRef){
        if(songRef instanceof Song){
            songs.add((Song) songRef);
        }else{
            throw new IllegalArgumentException("Input must be a song");
        }
    }

    public void playSongs(){
        System.out.println("-- " + this.name + " --");
        
        for(Song s : songs){
            System.out.println(s.toString());
        }
    }

}
