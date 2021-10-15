package com.charliebignell.app;

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Library {
    
    private static Library libraryInstance = null;

    private final String author;
    private List<Song> songList = null;
    private List<Album<Song>> albumList = null;
    private List<String> tagList = null;

    private Library(String author) {
        songList = new ArrayList<Song>();
        albumList = new ArrayList<Album<Song>>();
        tagList = new ArrayList<String>();
        this.author = author;
        this.getFromCSV();
    }

    public static Library getInstance(String authorInput) {
        if (libraryInstance == null) {
            libraryInstance = new Library(authorInput);
        }

        return libraryInstance;
    }

    public void getFromCSV() {
        String line = ""; // Represents the line of the csv currently being read

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/charliebignell/app/songs.csv"));
            boolean head = true;
            while ((line = br.readLine()) != null) {
                if (!head) {
                    String[] lineArr = line.split(",");
                    this.songList.add(new Song(lineArr[0], lineArr[1]));
                }else{
                    head = false;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAlbum(Album<Song> album){
        this.albumList.add(album);
    }

    public void addSong(Song song){
        this.songList.add(song);
    }

    public void addTag(String tag){
        this.tagList.add(tag);
    }

    public StringBuffer getSongs(String tag){
        StringBuffer buffer = new StringBuffer();
        for(Song s : songList){
            if(s.containsTag(tag)){
                buffer.append(s.toString());
            }
        }
        return buffer;
    }


    public void printTags() {
        System.out.println("-- Tags --");
        for (String tag : tagList) {
            System.out.println(tag);
        }
    }

    public void printSongs() {
        System.out.println("-- Songs --");
        for (Song song : songList) {
            System.out.println(song.toString());
        }
    }

    public void printAlbums() {
        System.out.println("-- Albums --");
        for (Album<Song> album : albumList) {
            System.out.println(album.toString());
        }
    }

    public void printLibrary() {
        System.out.println(this.author + "'s Music Library");
        System.out.println("-------------");
        printSongs();
        printAlbums();

    }
}
