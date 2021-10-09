package com.charliebignell.app;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Song {

    private final String name;
    private final String artist;
    private List<String> tags = new ArrayList<String>();

    public Song(String name, String artist, boolean temp) throws IllegalArgumentException {
        super();
        if (name == null || artist == null) {
            throw new IllegalArgumentException("Missing name or artist");
        }
        this.name = name;
        this.artist = artist;
        if(!temp){
            saveSong();
        }
    }

    public void saveSong() {
        try {
            FileWriter writer = new FileWriter("src/main/java/com/charliebignell/app/songs.csv", true);

            writer.append("\n" + this.getEntry());

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    @Override
    public String toString() {
        return this.name + ", by " + this.artist;
    }

    public void addTag(String tag) {
        this.deleteSong();
        this.tags.add(tag);
        this.saveSong();
    }

    public String getEntry() {
        StringBuffer tagsList = new StringBuffer();

        if (tags.isEmpty()) {
            tagsList.append("-");
        } else {
            for (int i = 0; i < tags.size() - 1; i++) {
                tagsList.append(tags.get(i) + "-");
            }
            tagsList.append(tags.get(tags.size() - 1));
        }

        return this.name + "," + this.artist + "," + tagsList;
    }

    public void deleteSong() {
        String line = "";
        List<String> songsToKeep = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("D:/Projects/Tagger/src/main/java/com/charliebignell/app/songs.csv"));

            while ((line = br.readLine()) != null) {
                if (!line.equals(this.getEntry())) {
                    songsToKeep.add(line);
                }
            }
            songsToKeep.remove(0);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileWriter writer = new FileWriter("src/main/java/com/charliebignell/app/songs.csv");
            writer.append("name,artist,tags");

            for (String s : songsToKeep) {
                writer.append("\n" + s);
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTag(String tag) {
        if (containsTag(tag)) {
            this.deleteSong();
            tags.remove(tag);
            this.saveSong();
        }
    }

    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

}
