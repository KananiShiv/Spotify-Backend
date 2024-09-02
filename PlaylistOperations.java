
import java.util.Scanner;

/**
 * The PlaylistOperations class represents a menu-driven program for managing a
 * playlist of songs and playlists themselves.
 * It provides options to add songs, print songs by artist, get song details,
 * remove songs, print all songs, get the size of the playlist, 
 * create new playlist, change the current playlist, clone a playlist, 
 * compare playlists and print all the playlists.
 * It uses Playlist class and SongRecord to
 * perform operations.
 * The user can interact with the program using this menu.
 * 
 * @author Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #1 for CSE 214, Summer 2023
 **/

public class PlaylistOperations {

    /**
     * Invariants:
     * counter always represents the number of playlist in the playlists[] array.
     * MAX_PLAYLISTS will always stay 50.
     */
    
    private static final int MAX_PLAYLISTS = 50; // The maximum number of songs that can be stored in a array.
    private static Playlist[] playlists = new Playlist[MAX_PLAYLISTS]; // Array for storing songs in the playlist.
    private static Playlist playlist = new Playlist(); //Current playlist being manipulated by the user.
    private static int counter = 1; // Number of songs currently in the playlist. 1 because playlist has been added to playlists[].

    /**
     * The main method for running the playlist operations program.
     * Prompts the user to select a menu option and performs the corresponding
     * operation.
     * 
     * @param args command-line arguments (not used in this program)
     * @throws IllegalArgumentException if the position is negative or greater than
     *                                  the maximum capacity
     * @throws FullPlaylistException    if the playlist is full
     *                                  Note: Position starts from 1.
     */
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        boolean flag = true;
        playlists[0] = playlist; //Current playlist being added to the array.

        System.out.println("Welcome to SongVerse Menu!");
        System.out.println("");
        System.out.println(playlist.getName() + " is currently selected");
        System.out.println("");

        while (flag) { // Will keep the menu keep running until user chooses to quit using Case "Q".
            System.out.println("A) Add Song ");
            System.out.println("B) Print Songs by Artist ");
            System.out.println("G) Get Song ");
            System.out.println("R) Remove Song  ");
            System.out.println("P) Print All Songs ");
            System.out.println("S) Size  ");
            System.out.println("N) Create a new playlist and set as current playlist");
            System.out.println("V) Change current playlist");
            System.out.println("C) Copy the current playlist's songs into a new playlist");
            System.out.println("E) Compare the songs in the current playlist with the given playlist");
            System.out.println("D) Print all Playlists");
            System.out.println("Q) Quit ");
            System.out.println("");
            System.out.println("Select a menu option:");

            String option = input.nextLine();

            switch (option.toUpperCase()) {

                case "A": // Adds song
                    System.out.println("Enter the song title:");
                    String title = input.nextLine();
                    System.out.println("Enter the song artist:");
                    String artist = input.nextLine();
                    System.out.println("Enter the song length (minutes): ");
                    int minutes = input.nextInt();
                    System.out.println("Enter the song length (seconds): ");
                    int seconds = input.nextInt();
                    input.nextLine(); // Is used to remove the remaining newline character ('\n') in the input
                    System.out.println("Enter the position: ");
                    int position = input.nextInt();
                    input.nextLine(); // Is used to remove the remaining newline character ('\n') in the input
                    SongRecord newsong = new SongRecord(title, artist, minutes, seconds);
                    try {
                        playlist.addSong(newsong, position);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (FullPlaylistException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Song Added: " + title + " by " + artist);
                    System.out.println("Select a menu option: ");
                    break;

                case "B": // Print Songs by Artist
                    System.out.println("Enter the artist: ");
                    String desiredartist = input.nextLine();
                    Playlist SongbyArtist;
                    try {
                        SongbyArtist = playlist.getSongsByArtist(playlist, desiredartist);
                        SongbyArtist.printAllSongs();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (FullPlaylistException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Select a menu option: ");
                    continue;

                case "G": // Get Song
                    System.out.println("Enter the position: ");
                    int insertpos = input.nextInt();
                    input.nextLine(); // Is used to remove the remaining newline character ('\n') in the input
                    SongRecord thesong = playlist.getSong(insertpos);
                    System.out.println(String.format("%-21s%-26s%19s%19s", "Song#", "Title", "Artist", "Length"));
                    System.out.println("-".repeat(85));
                    System.out.println(
                            String.format("%-21s%-39s%-19s%-6s", insertpos, thesong.getTitle(), thesong.getArtist(),
                                    thesong.getMinutes() + ":" + String.format("%02d", thesong.getSeconds())));
                    System.out.println("Select a menu option: ");
                    continue;

                case "R": // Remove Song
                    System.out.println("Enter the position: ");
                    int removepos = input.nextInt();
                    input.nextLine(); // Is used to remove the remaining newline character ('\n') in the input
                    if (removepos <= playlist.size()) {
                        playlist.removeSong(removepos);
                        System.out.println("Song Removed at position " + removepos);

                    } else
                        System.out.println("There is no song at position at " + removepos);
                    System.out.println("Select a menu option: ");
                    continue;

                case "P": // Print All Songs
                    playlist.printAllSongs();
                    System.out.println("Select a menu option: ");
                    continue;

                case "S": // Size
                    System.out.println("There are " + playlist.size() + " song(s) in the current playlist.");
                    System.out.println("Select a menu option: ");
                    continue;

                case "N": //Create a new playlist and set as current playlist
                    System.out.println("Enter the playlist name: ");
                    String newPlaylistName = input.nextLine();
                    Playlist newpPlaylist = new Playlist();
                    newpPlaylist.setName(newPlaylistName);
                    playlists[counter] = newpPlaylist;
                    playlist = newpPlaylist;
                    counter++;
                    System.out.println(
                            "New playlist " + newPlaylistName + " has been created and is currently selected.");
                    System.out.println("Select a menu option: ");
                    continue;

                case "V": //Change current playlist
                    System.out.println("Enter the playlist you want to use: ");
                    String newCurrentPlaylistName = input.nextLine();
                    Playlist newCurrentPlaylist = new Playlist();
                    boolean playlistFound = false;
                    for (int i = 0; i < playlists.length; i++) {
                        if (playlists[i] == null)
                        continue; 
                        if (playlists[i].getName().equals(newCurrentPlaylistName)) {
                            newCurrentPlaylist = playlists[i];
                            playlistFound = true;
                            break;
                        }
                    }
                    if (playlistFound) { 
                        playlist = newCurrentPlaylist;
                        System.out.println("Current playlist successfully changed to: " + newCurrentPlaylistName);
                    } else {
                        System.out.println("Playlist not found: " + newCurrentPlaylistName);
                    }
                    System.out.println("Select a menu option: ");
                    continue;

                case "C": //Copy the current playlist's songs into a new playlist
                    System.out.println("Enter the new playlist name: ");
                    String newCopyPlaylistName = input.nextLine();
                    Playlist newCopyPlaylist = new Playlist();
                    newCopyPlaylist.setName(newCopyPlaylistName);
                    newCopyPlaylist = (Playlist) playlist.clone();
                    playlists[counter] = newCopyPlaylist;
                    
                    counter++;
                    playlists[counter - 1].setName(newCopyPlaylistName);
                    System.out.println("Playlist successfully copied to " + newCopyPlaylistName);
                    System.out.println("Select a menu option: ");
                    continue;

                case "E": //Compare the songs in the current playlist with the given playlist
                    System.out.println("Enter the playlist name: ");
                    String compareToPlaylistName = input.nextLine();
                    Playlist compareToPlaylist = new Playlist();
                    boolean compare = false;
                    for (int i = 0; i < playlists.length; i++) {
                        if (playlists[i] == null)
                        continue; 
                        if (playlists[i].getName().equals(compareToPlaylistName)) {
                            compareToPlaylist = playlists[i];
                            compare = true;
                            break;
                        }
                    }
                    if (compareToPlaylist != null) {
                        if (playlist.equals(compareToPlaylist))
                            System.out.println(compareToPlaylistName + " is equal to the current playlist.");
                        else
                            System.out.println(compareToPlaylistName + " is not equal to the current playlist.");

                    } else
                        System.out.println("Playlist not found: " + compareToPlaylistName);
                    
                    System.out.println("Select a menu option: ");
                    continue;

                case "D": //Print all Playlists
                    System.out.println(String.format("%-21s%-26s%19s", "Playlist#", "Name", "Total Songs"));
                    System.out.println("-".repeat(66));
                    for (int i = 0; i < playlists.length; i++) { // iterates over every playlist in the playlists[].

                        if (playlists[i] != null) { // if playlist is not null, it prints the playlist.
                            System.out.println(String.format("%-21s%-39s%-19s", i + 1, playlists[i].getName(),
                                    playlists[i].size()));
                        }
                    }
                    System.out.println("");
                    System.out.println("Select a menu option: ");
                    continue;

                case "Q": // Quit
                    System.out.println("Thank you for using SongVerse! ");
                    flag = false;
                    input.close();
                    break;
                default: // If user types anything else it will default to Invalid Option
                    if (!option.equalsIgnoreCase("Q")) {
                        System.out.println("Invalid option");
                    }
                    break;

            }
        }
    }
}