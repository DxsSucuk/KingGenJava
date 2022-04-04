package wtf.kinggen.exceptions;

/**
 * Exception for an Invalid or Blacklisted Key.
 */
public class KingGenInvalidApiKeyException extends Exception {

    /**
     * Generates a generic Invalid-Key Exception.
     */
    public KingGenInvalidApiKeyException() {
        super("The provided API-Key is Invalid or Blacklisted.");
    }

}
