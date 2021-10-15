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
        this.getFromCSV();
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
        this.albumList.add(album);
    }

    /**
     * Add a song to the library
     * 
     * @param song the song to add
     */
    public void addSong(Song song) {
        if (!songList.contains(song)) {
            this.songList.add(song);
        }
    }

    /**
     * Remove a song from the library
     * 
     * @param song the song to remove
     */
    public void removeSong(Song song) {
        if (songList.contains(song)) {
            this.songList.remove(song);
        }
    }

    /**
     * Get the a list of the songs that contains a specific tag
     * 
     * @param tag the tag to search for
     * @return the list of songs
     */
    public StringBuffer getSongs(String tag) {
        StringBuffer buffer = new StringBuffer();
        for (Song s : songList) {
            if (s.containsTag(tag)) {
                buffer.append(s.toString());
            }
        }
        return buffer;
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
        for (Song song : songList) {
            System.out.println(song.toString());
        }
    }

    /**
     * Print the albums
     */
    public void printAlbums() {
        System.out.println("-- Albums --");
        for (Album<Song> album : albumList) {
            System.out.println(album.toString());
        }
    }

    /**
     * Print a summary of the library
     */
    public void printLibrary() {
        System.out.println(this.author + "'s Music Library");
        System.out.println("-------------");
        printSongs();
        printAlbums();

    }
}
