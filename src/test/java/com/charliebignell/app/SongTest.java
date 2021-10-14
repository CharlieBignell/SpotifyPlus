package com.charliebignell.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class SongTest {
    private Song song = new Song("name", "artist", false);

    @Before
    public void add_tag(){
        song.addTag("tag");
    }

    @After
    public void remove_tag(){
        song.removeTag("tag");
    }

    @Test
    public void get_song_name_and_artist() {
        assertEquals("artist", song.getArtist());
        assertEquals("name", song.getName());
        assertEquals("name, by artist", song.toString());
    }

    @Test
    public void add_and_remove_tags() {
        song.addTag("new tag");
        assertTrue("Tag not added", song.containsTag("new tag"));
        song.removeTag("new tag");
        assertFalse("Tag not removed", song.containsTag("new tag"));
    }

    @Test
    public void check_contains_method(){
        assertEquals(song.containsTag("non-existent"), false);
        assertEquals(song.containsTag(""), false);
        assertEquals(song.containsTag("tag"), true);
    }

    @Test
    public void save_song_to_csv(){
        String line = "";
        boolean containsFlag = false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("D:/Projects/Tagger/src/main/java/com/charliebignell/app/songs.csv"));

            while ((line = br.readLine()) != null) {
                if (line.equals(song.getEntry())) {
                    containsFlag = true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue("Song failed to save", containsFlag);
    }

    @Test
    public void delete_song(){
        String line = "";
        boolean containsFlag = false;

        Song newSong = new Song("newName", "newArtist", false);
        newSong.deleteSong();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/com/charliebignell/app/songs.csv"));

            while ((line = br.readLine()) != null) {
                if (line.equals(newSong.getEntry())) {
                    containsFlag = true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertFalse("Song failed to delete", containsFlag);
    }

    @Test 
    public void get_entry(){
        assertEquals(song.getEntry(), "name,artist,tag");
    }

    @Test
    public void check_toString(){
        assertEquals(song.toString(), "name, by artist");
    }

}
