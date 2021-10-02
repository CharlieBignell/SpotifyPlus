package com.charliebignell.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MainTest {
    private Song song = new Song("name", "artist");

    @Test
    public void songGetters() {
        assertEquals("artist", song.getArtist());
        assertEquals("name", song.getName());
        assertEquals("name, by artist", song.getFormattedSong());
    }

    @Test
    public void addAndRemoveTagsToSongs() {
        song.addTag("tag");
        
    }

}
