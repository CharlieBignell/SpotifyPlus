package com.charliebignell.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AudioTest {
    private Audio audio = new Audio("name", "artist");

    @Test
    public void get_name_and_artist() {
        assertEquals("name", audio.getName());
        assertEquals("artist", audio.getArtist());

    }
}
