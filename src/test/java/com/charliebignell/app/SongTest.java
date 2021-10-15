package com.charliebignell.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SongTest {
    private Song song;

    @Before
    public void setUp() {
        song = new Song("name", "artist");
        song.addTag("tag");
    }

    @Test
    public void add_and_remove_tags() {
        song.addTag("new tag");
        assertTrue("Tag not added", song.containsTag("new tag"));
        song.removeTag("new tag");
        assertFalse("Tag not removed", song.containsTag("new tag"));
    }

    @Test
    public void add_and_remove_tags_integer() {
        song.addTag(1);
        assertTrue("Tag not added", song.containsTag("1"));
        song.removeTag(1);
        assertFalse("Tag not removed", song.containsTag("new tag"));
    }

    @Test
    public void check_contains_method() {
        assertEquals(song.containsTag("non-existent"), false);
        assertEquals(song.containsTag(""), false);
        assertEquals(song.containsTag("tag"), true);
    }

    @Test
    public void get_song_name_and_artist() {
        assertEquals("artist", song.getArtist());
        assertEquals("name", song.getName());
        assertEquals("name, by artist", song.toString());
    }

    @Test
    public void get_tags() {
        song.addTag("tag2");
        assertEquals("tag,tag2", song.getTags());
    }

}
