package com.charliebignell.app;

import java.io.PrintStream;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class PlaylistTest {
    private Song song1 = new Song("name1", "artist1");
    private Song song2 = new Song("name2", "artist2");
    private Library lib = Library.getInstance("libName");

    private Playlist<String> playlist;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        playlist = new Playlist<String>();
        lib.addSong(song1);
        lib.addSong(song2);
        song1.addTag("tag1");
        song2.addTag("tag1");
        song2.addTag("tag2");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
        lib.removeSong(0);
        lib.removeSong(1);
    }

    @Test
    public void check_populate_and_play() {
        Song newSong = new Song("name3", "artist3");
        lib.addSong(newSong);
        newSong.addTag("tag3");
        playlist.populate("tag3");
        playlist.playSongs(lib);
        assertEquals("Playing name3, by artist3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check_depopulate() {
        playlist.populate("tag1");
        playlist.populate("tag2");
        playlist.dePopulate("tag1");
        playlist.playSongs(lib);
        assertEquals("Playing name2, by artist2", outputStreamCaptor.toString().trim());
    }
}
