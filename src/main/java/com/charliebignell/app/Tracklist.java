package com.charliebignell.app;

public interface Tracklist <T> {

    abstract void populate(T songRef);
    abstract void playSongs(Library lib);

}
