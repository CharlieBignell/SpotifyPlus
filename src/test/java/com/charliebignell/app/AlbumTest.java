package com.charliebignell.app;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class AlbumTest {
    private Song song1 = new Song("name1", "artist1", false);
    private Album<Song> album = new Album<Song>("albumName");

    private final PrintStream outContent = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void tearDown() {
        System.setOut(outContent);
    }

    @Test
    public void check_populate() {
        assertEquals(0, album.getSize());
        album.populate(song1);
        assertEquals(1, album.getSize());

    }
}
