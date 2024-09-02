
/**
 * The SongRecord class is used to represent a song with its title, artist, duration in minutes and seconds.
 * The methods in this class are used to set and retrieve the properties of a song. 
 * It also implements Cloneable Interface to use clone method.
 * 
 * @author Shiv Kanani
 * SBU ID: 115171965
 * Homework #1 for CSE 214, Summer 2023
 **/

public class SongRecord implements Cloneable {

    private String title; // The title of the song
    private String artist; // The artist of the song
    private int minutes; // The duration in minutes
    private int seconds; // The duration in seconds

    /**
     * Constructs an empty SongRecord object.
     */

    public SongRecord() {
    }

    /**
     * Constructs a SongRecord object with the specified title, artist, minutes, and
     * seconds.
     *
     * @param title   the title of the song
     * @param artist  the artist of the song
     * @param minutes the duration in minutes
     * @param seconds the duration in seconds
     * 
     */

    public SongRecord(String title, String artist, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Gets the title of the song.
     *
     * @return The title of the song
     */

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The title of the song
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the artist of the song.
     *
     * @return The artist of the song
     */

    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist The artist of the song
     */

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Gets the duration in minutes.
     *
     * @return The duration in minutes
     */

    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the duration in minutes.
     *
     * @param minutes The duration in minutes
     * @throws IllegalArgumentException If minutes is negative
     */

    public void setMinutes(int minutes) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative. Please try again.");
        } else
            this.minutes = minutes;
    }

    /**
     * Gets the duration in seconds.
     *
     * @return The duration in seconds
     */

    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the duration in seconds.
     *
     * @param seconds The duration in seconds
     * @throws IllegalArgumentException If seconds is negative or greater than 59
     */

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds cannot be negative and greater than 59!");
        } else
            this.seconds = seconds;
    }

    /**
     * Checks if the given object is equal to this SongRecord.
     *
     * @param obj an object to which this SongRecord is being compared with
     * @return A return value of true indicates that obj refers to a
     *         SongRecord object with the same elements as this SongRecord.
     *         Otherwise, the return value is false.
     * 
     */

    public boolean equals(Object obj) {
        if (obj instanceof SongRecord) { // First checks the object is a SongRecord
            SongRecord current = (SongRecord) obj; // Cast the object to a SongRecord
            return (current.title.equals(this.title) && current.artist.equals(this.artist)
                    && current.minutes == this.minutes && current.seconds == this.seconds);
        } else
            return false;
    }

    /**
     * Generates a deep copy of this SongRecord object.
     * 
     * @return The return value is a copy of this SongRecord. Subsequent changes
     *         to the copy will not affect the original, nor vice versa.
     *         Note:
     *         The return value must be typecast to a SongRecord before it can be
     *         used.
     * @throws CloneNotSupportedException if cloning is not supported for the object
     */

    public Object clone() throws CloneNotSupportedException {
       
            SongRecord cloneSongRecord = (SongRecord) super.clone();
            cloneSongRecord.title = this.title;
            cloneSongRecord.artist = this.artist;
            cloneSongRecord.minutes = this.minutes;
            cloneSongRecord.seconds = this.seconds;
            return cloneSongRecord;
       
    }

    /**
     * Returns a string representation of the SongRecord object.
     *
     * @return A string representation of the SongRecord object
     */

    public String toString() {
        return String.format("%-21s%-26s%19s%19s", title, artist, minutes, seconds);
    }
}
