package com.charliebignell.app;

import java.util.Scanner;

public class Main {
    private static Library lib = null;
    private static Scanner input = new Scanner(System.in);

    public static void getOptions() {

        // If the librry hsn't be initialised, initialise it then re-run the method
        if (lib == null) {
            System.out.println("Please type your name and press enter:");
            lib = Library.getInstance(input.nextLine());
            lib.getFromCSV();
            lib.printSummary();
            getOptions();
        } else {

            // Give the options to the user
            System.out.println("\nWhat would you like to do? Type the number of your selection and press enter:");
            System.out.println("[1] - Add a song");
            System.out.println("[2] - Remove a song");
            System.out.println("[3] - Add an album");
            System.out.println("[4] - Remove an album");
            System.out.println("[5] - Add a tag to a song");
            System.out.println("[6] - Remove a tag to a song");
            System.out.println("[7] - View library");
            System.out.println("[8] - Play an album");
            System.out.println("[9] - Play songs using tags");
            System.out.println("[10] - Exit\n");

            // Get the selection
            int selection = input.nextInt();
            input.nextLine();

            boolean exit = false;

            switch (selection) {
                case 1:
                    System.out.println("Selected: Add a song\n");

                    // Get the name and artist
                    System.out.println("Please enter the name the song:");
                    String name = input.nextLine();
                    System.out.println("Please enter the song's artist:");
                    String artist = input.nextLine();

                    // Add the song from the given input
                    lib.addSong(new Song(name, artist));

                    break;

                case 2:
                    System.out.println("Selected: Remove a song\n");

                    // Print the songs, so that the user can find the ID of the song they want to remove
                    lib.printSongs();

                    // Get the ID and remove it
                    System.out.println("\nPlease enter the ID of the song you want to remove:");
                    int songIndex_remove = input.nextInt();
                    lib.removeSong(songIndex_remove);

                    break;

                case 3:
                    System.out.println("Selected: Add an album\n");

                    // Get the name of the album, and initialise it
                    System.out.println("Please enter the name the album:");
                    Album<Song> album = new Album<Song>(input.nextLine());
                    lib.addAlbum(album);

                    // Print all songs, so the user can select the IDs of the songs to add to the album
                    lib.printSongs();

                    // Get the song IDs to add to the album
                    System.out.println("\nPlease enter the song IDs to add to this album, separate each number with a comma only:");
                    String[] songsToAdd = input.nextLine().split(",");

                    // Add the songs, if the ID is valid
                    for (String i : songsToAdd) {
                        if (Integer.parseInt(i) < lib.getSongCount()) {
                            album.populate(lib.getSong(Integer.parseInt(i)));
                        }
                    }

                    System.out.println("Sucessfully added " + album.getSize() + " song(s)");
                    break;

                case 4:
                    System.out.println("Selected: Remove an album\n");

                    // Print the albums so the user can select the album they want to remove
                    lib.printAlbums();

                    // Get the album to remove, and remove it
                    System.out.println("\nPlease enter the ID of the album you want to remove:");
                    int albumIndex = input.nextInt();
                    lib.removeAlbum(albumIndex);

                    break;

                case 5:
                    System.out.println("Selected: Add a tag to a song\n");

                    // Print the songs so the user can select the song to add the tag to
                    lib.printSongs();

                    // Get the songs to add the tags to in a comma-separated string
                    System.out.println("\nPlease enter the IDs of the songs you want to add the tag to, separate each number with a comma only:");
                    String[] songsToAdd_tags = input.nextLine().split(",");

                    // Get the tag to add to the songs
                    System.out.println("\nPlease enter the tag you want to add to the song(s)");
                    String tagToAdd = input.nextLine();

                    // If the song ID is valid, add the tag
                    for (String i : songsToAdd_tags) {
                        if (Integer.parseInt(i) < lib.getSongCount()) {
                            lib.getSong(Integer.parseInt(i)).addTag(tagToAdd);
                            ;
                        }
                    }

                    System.out.println("Added tag to songs");
                    break;

                case 6:
                    System.out.println("Selected: Remove a tag from a song\n");

                    // Print the songs so the user can select the IDs to remove the tag from
                    lib.printSongs();

                    // Get the IDs of the songs to remove the tags from
                    System.out.println("\nPlease enter the IDs of the songs you want to remove the tag from, separate each number with a comma only:");
                    String[] songsToRemove_tags = input.nextLine().split(",");

                    // Get the tag to remove
                    System.out.println("\nPlease enter the tag you want to remove from the song(s)");
                    String tagToRemove = input.nextLine();

                    // If the song ID is valid, remove the tag
                    for (String i : songsToRemove_tags) {
                        if (Integer.parseInt(i) < lib.getSongCount()) {
                            lib.getSong(Integer.parseInt(i)).removeTag(tagToRemove);
                            ;
                        }
                    }

                    System.out.println("Removed tag from songs");
                    break;

                case 7:
                    System.out.println("Selected: View library\n");
                    lib.printLibrary();
                    break;

                case 8:
                    System.out.println("Selected: Play an album\n");

                    // Print the albums so the user can select the ID of the alnum to play
                    lib.printAlbums();

                    // Get the album ID
                    System.out.println("\nPlease enter the ID of the album you want to play:");
                    int albumIndex_play = input.nextInt();

                    // If the ID is valid, play the songs
                    if (albumIndex_play < lib.getAlbumCount()) {
                        lib.getAlbum(albumIndex_play).playSongs(lib);
                    } else {
                        System.out.println("Invalid album ID");
                    }

                    break;

                case 9:
                    System.out.println("Selected: Play songs using tags");

                    // Print all assigned tags, so the user can select the tags they want to play
                    lib.printTags();

                    
                    System.out.println("\nPlease enter the tags you want to play, separated with a comma only:");
                    String[] tagsToPlay = input.nextLine().split(",");
                    Playlist<String> p = new Playlist<String>();
                    for (String tag : tagsToPlay) {
                        p.populate(tag);
                    }
                    p.playSongs(lib);
                    break;

                case 10:
                    exit = true;
                    break;

                default:
                    System.out.println("ERROR: Please select a number 1-10");
            }
            if (!exit) {
                getOptions();
            }

        }

    }

    public static void main(String[] args) {

        System.out.println("-=- TAGGER -=-");
        getOptions();

    }
}
