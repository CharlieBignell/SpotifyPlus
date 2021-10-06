package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;

public class Album extends Tracklist {
    
    public Album(String name){
        super(name);
    }

    public void addSong(Song song){
        System.out.println("Added ___ to " + this.name);
    }
    
}
