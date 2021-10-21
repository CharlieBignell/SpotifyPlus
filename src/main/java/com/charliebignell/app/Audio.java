package com.charliebignell.app;

public class Audio {

    protected final String name;
    protected final String artist;

    /**
     * Audio Constructor
     * 
     * @param name   The name of the song
     * @param artist The song's artist
     * @throws IllegalArgumentException
     */
    public Audio(String name, String artist) throws IllegalArgumentException {
        super();
        if (name == "" || artist == "") {
            throw new IllegalArgumentException("Missing name or artist");
        }
        this.name = name;
        this.artist = artist;
    }

    /**
     * Get the name of an audio
     * 
     * @return The audio name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the audio's artist
     * 
     * @return The audio's artist
     */
    public String getArtist() {
        return this.artist;
    }

}
