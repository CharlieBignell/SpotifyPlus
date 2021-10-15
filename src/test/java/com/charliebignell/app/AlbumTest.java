package com.charliebignell.app;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class AlbumTest {
    private Song song1 = new Song("name1", "artist1");
    private Album<Song> album;

    private final PrintStream outContent = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        album = new Album<Song>("albumName");
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void tearDown() {
        System.setOut(outContent);
    }

    @Test
    public void check_populate_and_getSize() {
        assertEquals(0, album.getSize());
        album.populate(song1);
        assertEquals(1, album.getSize());

    }

    @Test
    public void check_toString() {
        album.populate(song1);
        assertEquals("albumName, contains 1 song(s)", album.toString());
    }
}
