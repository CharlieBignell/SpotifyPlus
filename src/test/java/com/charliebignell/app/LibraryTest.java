package com.charliebignell.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LibraryTest {

    Library lib = Library.getInstance("myName");

    @Test
    public void check_singleton_and_get_author() {
        Library newLibInstance = Library.getInstance("newName");
        assertEquals("myName", newLibInstance.getAuthor());
    }

    @Test
    public void populate_from_csv_and_song_count() {
        int startSongCount = lib.getSongCount();
        lib.getFromCSV();
        assertEquals(startSongCount + 3, lib.getSongCount() + startSongCount);
    }

    @Test
    public void add_album_and_album_count() {
        assertEquals(0, lib.getAlbumCount());
        lib.addAlbum(new Album<Song>("albumName"));
        assertEquals(1, lib.getAlbumCount());
    }

    @Test
    public void add_song_and_remove_song() {
        int startSongCount = lib.getSongCount();
        lib.addSong(new Song("newSongName", "newSongArtist"));
        assertEquals(startSongCount + 1, lib.getSongCount() + startSongCount);
        lib.removeSong(startSongCount);
        assertEquals(startSongCount, lib.getSongCount() + startSongCount);

    }

    @Test
    public void remove_album() {
        int startAlbumCount = lib.getAlbumCount();
        lib.addAlbum(new Album<Song>("albumName"));
        lib.removeAlbum(startAlbumCount);
        assertEquals(startAlbumCount, lib.getAlbumCount());
    }

    @Test
    public void get_song() {
        Song s = new Song("newSongName", "newSongArtist");
        lib.addSong(s);
        assertEquals(s, lib.getSong(lib.getSongCount() - 1));
    }

    @Test
    public void get_album() {
        Album<Song> a = new Album<Song>("albumName");
        lib.addAlbum(a);
        assertEquals(a, lib.getAlbum(lib.getAlbumCount() - 1));
    }
}
