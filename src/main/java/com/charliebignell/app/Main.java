package com.charliebignell.app;

public class Main {

    public static void main(String[] args) {
        Song s = new Song("play4/4", "test2", false);
        s.addTag("tbt");
        s.addTag("rock");
        s.removeTag("rock");
        Song s2 = new Song("dontplay", "test2", false);
        s2.addTag("tbt");
        s2.addTag("rock");
        s2.removeTag("tbt");
        Playlist<String> p = new Playlist<String>();
        p.populate("tbt");
        p.populate("rap");
        p.playSongs();

    }
}
