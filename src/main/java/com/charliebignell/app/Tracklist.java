package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;

public abstract class Tracklist {

    protected List<Song> songs = null;
    protected String name = null;

    public Tracklist(String name){
        super();
        this.name = name;
    }

    abstract void addSong(Song song);

    public void playSongs(){
        for(Song i : songs){
            System.out.println(i.toString());
        }
    };

}
