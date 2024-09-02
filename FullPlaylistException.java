
/**
 * The FullPlaylistException class is a custom exception thrown when attempting to add a song to a full playlist.
 * It extends the Exception class and provides a constructor to set the exception message.
 * This exception is used in the Playlist class to handle cases where the playlist has reached its maximum capacity.
 * 
 * @author Shiv Kanani
 * SBU ID: 115171965
 * Homework #1 for CSE 214, Summer 2023
 **/

public class FullPlaylistException extends Exception {

    /**
     * Constructs a new FullPlaylistException with the specified message.
     * @param message the message of the exception.
     */

    public FullPlaylistException(String message) {
        super(message);
    }
}
