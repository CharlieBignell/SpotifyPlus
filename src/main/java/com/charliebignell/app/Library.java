package com.charliebignell.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Library {

    private static Library libraryInstance = null;

    private final String author;

    private List<Song> songList = null;
    private List<Album<Song>> albumList = null;

    /**
     * Library constructor. Private as this is a singleton class
     * 
     * @param author the author of the library
     */
    private Library(String author) {
        songList = new ArrayList<Song>();
        albumList = new ArrayList<Album<Song>>();
        this.author = author;
    }

    /**
     * Get the single library instance if it exists, if it doesn't then create one
     * 
     * @param author the author of the library
     * @return the library instance
     */
    public static Library getInstance(String author) {
        if (libraryInstance == null) {
            libraryInstance = new Library(author);
        }

        return libraryInstance;
    }

    public String getAuthor(){
        return this.author;
    }

    /**
     * Read the CSV file, and populate the library if it contains any songs
     */
    public void getFromCSV() {
        String line = ""; // Represents the line of the csv currently being read

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/charliebignell/app/songs.csv"));
            boolean head = true;
            while ((line = br.readLine()) != null) {
                if (!head) {
                    String[] lineArr = line.split(",");
                    this.songList.add(new Song(lineArr[0], lineArr[1]));
                } else {
                    head = false;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add an album to the library
     * 
     * @param album the album to add
     */
    public void addAlbum(Album<Song> album) {
        if (!albumList.contains(album)) {
            this.albumList.add(album);
        } else {
            System.out.println("\nThis album already exists!\n");

        }
    }

    /**
     * Add a song to the library
     * 
     * @param song the song to add
     */
    public void addSong(Song song) {
        if (!songList.contains(song)) {
            this.songList.add(song);
        } else {
            System.out.println("\nThis song already exists!");

        }
    }

    /**
     * Remove a song from the library
     * 
     * @param i the index of the song to remove
     */
    public void removeSong(int i) {
        if (songList.contains(songList.get(i))) {
            this.songList.remove(songList.get(i));
        } else {
            System.out.println("\nInvalid song ID");
        }
    }

    /**
     * Remove an album from the library
     * 
     * @param i the index of the album to remove
     */
    public void removeAlbum(int i) {
        if (albumList.contains(albumList.get(i))) {
            this.albumList.remove(albumList.get(i));
        } else {
            System.out.println("\nInvalid album id");
        }
    }

    /**
     * Get the a list of the songs that contains a specific tag
     * 
     * @param tag the tag to search for
     * @return the list of songs
     */
    public void playSongsFromTag(String tag) {
        for (Song s : songList) {
            if (s.containsTag(tag)) {
                System.out.println("Playing " + s.getName() + ", by " + s.getArtist());
            }
        }
    }

    /**
     * Get song with a given index
     * 
     * @param i the index of the song to get
     * @return the song with the given index
     */
    public Song getSong(int i) {
        return this.songList.get(i);
    }

    /**
     * Get an album with a given index
     * 
     * @param i the index of the album to get
     * @return the album with the given index
     */
    public Album<Song> getAlbum(int i) {
        return this.albumList.get(i);
    }

    /**
     * Get the number of albums
     * 
     * @return the size of the album list
     */
    public int getAlbumCount() {
        return this.albumList.size();
    }

    /**
     * Get the number of songs in the library
     * 
     * @return the size of the song list
     */
    public int getSongCount() {
        return this.songList.size();
    }

    /**
     * Print a list of all the tags attached to songs
     */
    public void printTags() {
        Set<String> tagList = new HashSet<String>();
        System.out.println("-- Tags --");

        // Get the list of tags for each song and add to set. Use of a set means we
        // don't need to worry about duplicates
        for (Song s : songList) {
            String[] tagArr = s.getTags().split(",");
            for (String tag : tagArr) {
                tagList.add(tag);
            }
        }

        // Print the list
        for (String tag : tagList) {
            System.out.println(tag);
        }
    }

    /**
     * Print the songs
     */
    public void printSongs() {
        System.out.println("-- Songs --");
        int i = 0;
        for (Song song : songList) {
            System.out.println(i + ": " + song.toString());
            i++;
        }
    }

    /**
     * Print the albums
     */
    public void printAlbums() {
        System.out.println("\n-- Albums --");
        int i = 0;
        for (Album<Song> album : albumList) {
            System.out.println(i + ": " + album.toString());
            i++;
        }
    }

    /**
     * Print the whole library
     */
    public void printLibrary() {
        System.out.println("--------------------");
        System.out.println(this.author + "'s Music Library\n");
        printSongs();
        printAlbums();

    }

    /**
     * Print a summary of the library
     */
    public void printSummary() {
        System.out.println("--------------------");
        System.out.println(this.author + "'s Music Library");
        System.out.println(songList.size() + " song(s)");
        System.out.println(albumList.size() + " album(s)");
    }
}
