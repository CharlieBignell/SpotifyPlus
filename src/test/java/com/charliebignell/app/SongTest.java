package com.charliebignell.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SongTest {
    private Song song = new Song("name", "artist", false);

    @Test
    public void songGetters() {
        assertEquals("artist", song.getArtist());
        assertEquals("name", song.getName());
        assertEquals("name, by artist", song.toString());
    }

    @Test
    public void addAndRemoveTagsToSongs() {
        song.addTag("tag");
        assertEquals(song.containsTag("tag"), true);
        song.removeTag("tag");
        assertEquals(song.containsTag("tag"), false);
    }

}
