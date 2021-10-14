package com.charliebignell.app;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class PlaylistTest {
    private Song song1;
    private Song song2;
    private Playlist<String> playlist;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {

        // Clear the csv file so it's clean for each test
        try {
            FileWriter writer = new FileWriter("src/main/java/com/charliebignell/app/songs.csv", false);
            writer.append("name,artist,tags");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        song1 = new Song("name1", "artist1", true);
        song2 = new Song("name2", "artist2", true);
        playlist = new Playlist<String>();

        song1.addTag("tag1");
        song2.addTag("tag1");
        song2.addTag("tag2");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void check_populate_and_play() {
        playlist.populate("tag2");
        playlist.playSongs();
        assertEquals(song2.toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void check_depopulate() {
        playlist.populate("tag2");
        playlist.populate("tag1");
        playlist.dePopulate("tag1");
        playlist.playSongs();
        assertEquals(song2.toString(), outputStreamCaptor.toString().trim());
    }
}
