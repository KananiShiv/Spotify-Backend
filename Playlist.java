
/**
 * The Playlist class is used to represent a playlist of songs. 
 * It provides methods to manage and manipulate the playlist. 
 * It also implements Cloneable Interface to use clone method.
 *
 * @author Shiv Kanani
 * SBU ID: 115171965
 * Homework #1 for CSE 214, Summer 2023
 **/

public class Playlist implements Cloneable {

    /**
     * Invariants:
     * items always represents the number of songs in the array.
     * MAX_SONGS will always stay 50.
     */

    private final int MAX_SONGS = 50;  // The maximum number of songs that can be stored in a array.
    private SongRecord[] songs;  // Array for storing songs in the playlist.
    private int items;  // Number of songs currently in the playlist.
    private String name; // The name of the playlist.

    /**
     * Constructs an empty Playlist object with the default maximum capacity.
     */

    public Playlist() {
        songs = new SongRecord[MAX_SONGS];
        items = 0;
        name = "OriginalPlaylist";
    }

    /**
     * Constructs a Playlist object with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the playlist
     * @throws IllegalArgumentException if the initial capacity is negative
     */

    public Playlist(int initialCapacity) {

        if (initialCapacity < 0)
            throw new IllegalArgumentException();
        songs = new SongRecord[initialCapacity];
        items = 0;
        name = "OriginalPlaylist";
    }

    /**
     * Constructs a Playlist object with the specified name.
     *
     * @param name the name of the playlist
     */

    public Playlist(String name){
        this.name = name;
        songs = new SongRecord[MAX_SONGS];
        items = 0;
    }

    /**
     * Gets the maximum capacity of the playlist.
     *
     * @return the maximum capacity of the playlist
     */

    public int getCapacity() {
        return songs.length;
    }

    /**
     * Gets the number of songs in the playlist.
     *
     * @return the number of songs in the playlist
     * @throws IllegalStateException if the playlist object is not instantiated
     */

    public int size() {
        if (songs == null)
            throw new IllegalStateException("Please instantiate the Playlist object");
        return items;
    }

    /**
     * Checks if the given object is equal to this Playlist.
     *
     * @param obj the object to which this Playlist is being compared with
     * @return A return value of true indicates that obj refers to a
     *         Playlist object with the same elements as this Playlist.
     *         Otherwise, the return value is false.
     */

    public boolean equals(Object obj) {

        if (obj instanceof Playlist) {  //First checks the object is a Playlist
            Playlist current = (Playlist) obj;  //Cast the object to a Playlist
            if (current.songs.length != this.songs.length)  //Checks whether the size is equal
                return false;

            for (int i = 0; i < songs.length; i++) {    //Checks each song in the playlist manually
                SongRecord compareFrom = this.songs[i];
                SongRecord compareTo = current.songs[i];
                if (compareFrom == null && compareTo == null)
                   continue; 
                if (!compareFrom.equals(compareTo)) 
                    return false; //Calls equals method from SongRecord to check equality between songs                                           
            }
            return true;
        }
        return false;
    }

    /**
     * Generates a deep copy of this Playlist object.
     * 
     * @return The return value is a copy of this Playlist. Subsequent changes
     *         to the copy will not affect the original, nor vice versa.
     *         Note:
     *         The return value must be typecast to a Playlist before it can be
     *         used.
     * @throws CloneNotSupportedException if the Playlist class does not implement Cloneable Interface.
    */

    public Object clone() throws CloneNotSupportedException {

        Playlist clonePlaylist = new Playlist();
        clonePlaylist.items = this.items;
        clonePlaylist.songs = new SongRecord[this.songs.length];
        for (int i = 0; i < items; i++) {   //Manually clone each song in the playlist
            clonePlaylist.songs[i] = (SongRecord) songs[i].clone(); //Use clone method in SongRecord class.
        }
        return clonePlaylist; //Return the new Object.

    }

    /**
     * Adds a song to the playlist at the specified position.
     *
     * @param song the song to add
     * @param position the position at which to add the song 
     * Precondition: This Playlist has been instantiated and the SongRecord objects are less than 50.
     * Postcondition: The new song is add at the desired position and the songs that were originally in 
     *                positions greater than or equal to position are moved back one position.
     * @throws IllegalArgumentException if the position is invalid (negative or greater than the maximum capacity)
     * @throws FullPlaylistException if the playlist is full
     * Note: Position starts from 1.
     */

    public void addSong(SongRecord song, int position) throws Exception {

        if ((position < 0 || position > 50))
            throw new IllegalArgumentException("Position cannot be negative and more than 50. Please try again.");
        if (songs.length == items)
            throw new FullPlaylistException("The Playlist is full!");

        for (int i = items; i >= position - 1; i--) {   //Shifts the elements in the playlist to the right.
            songs[i + 1] = songs[i];
        }
        songs[position - 1] = song; //Adds the song to the playlist.
        items++; 
    }

    /**
     * Removes a song from the playlist at the specified position.
     *
     * @param position the position from which to remove the song 
     * Precondition: This Playlist has been instantiated and the position is less than the number of songs.
     * Postcondition: The song is removed from the desired position and The songs that were originally in 
     *                positions greater than or equal to position are moved forward one position.
     * @throws IllegalArgumentException if the position is invalid (negative or greater than the number of songs)
     * Note: Position starts from 1.
     */

    public void removeSong(int position) {
        if (position < 0 || position > items)
            throw new IllegalArgumentException("Position cannot be negative and more than 50. Please try again.");
        for (int i = position - 1; i < songs.length - 1; i++) { //Shifts the elements to the left.
            songs[i] = songs[i + 1];
        }
        items--;

    }

    /**
     * Gets the song at the specified position in the playlist.
     *
     * @param position the position of the song to retrieve 
     * @return the song at the specified position
     * Precondition: This Playlist has been instantiated and the position is greater than 0 
     * 				 and less than the number of songs in the playlist.
     * @throws IllegalArgumentException if the position is invalid (negative or greater than the number of songs)
     * Note: Positions starts from 1.
     */

    public SongRecord getSong(int position) {
        if (position < 0 || position > items)
            throw new IllegalArgumentException("Position cannot be negative and more than 50. Please try again.");
        return songs[position - 1];
    }

    /**
     * Prints all the songs in the playlist.
     * Precondition: This Playlist Object has been instantiated.
     * Postcondition: A formatted table of each song in the playlist is printed.
     */

    public void printAllSongs() {

        String playlistDetails = this.toString(); //Calls toString() method of the Playlist class.
        System.out.println(playlistDetails);
    }

    /**
     * Retrieves a subset of songs from the playlist by the specified artist.
     *
     * @param originalList the original playlist to search in
     * @param artist the artist name to match
     * @return a new playlist containing songs by the specified artist
     * Preconditions: The Playlist referred to by the originalList has been instantiated.
     * @throws IllegalArgumentException if the position is invalid (negative or greater than the maximum capacity)
     * @throws FullPlaylistException if the playlist is full
     */

    public Playlist getSongsByArtist(Playlist originalList, String artist) throws Exception {

        Playlist matchingSongs = new Playlist();
        int counter = 0;
        for (int i = 0; i < items; i++) {

            if (songs[i].getArtist().equals(artist)) {
                counter++;
                matchingSongs.addSong(songs[i], counter);
            }
        }
        return matchingSongs;
    }

    /**
     * Sets the name of the playlist.
     *
     * @param name the name of the playlist
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the playlist.
     *
     * @return the name of the playlist
     */

    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the Playlist object in a neatly formatted table.
     *
     * @return a string representation of the Playlist object
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-21s%-26s%19s%19s", "Song#", "Title", "Artist", "Length"))
          .append(System.lineSeparator());  //Prints the Song#, Title, Artist & Length in one line
        sb.append("-".repeat(85)).append(System.lineSeparator()); //Prints dashes 85 times

        for (int i = 0; i < songs.length; i++) { //iterates over every song in the playlist to print each song.

            if (songs[i] != null) { //if song is not null, it prints the song.
                sb.append(String.format("%-21s%-39s%-19s%-6s", i + 1, songs[i].getTitle(), songs[i].getArtist(),
                  songs[i].getMinutes() + ":" + String.format("%02d", songs[i].getSeconds())))
                  .append(System.lineSeparator());
            }

        }

        return sb.toString(); //returns the String representation of Playlist Object using a StringBuilder.
    }
    
}
