package wtf.kinggen.exceptions;

public class KingGenReachedLimitException extends Exception {

    /**
     * Generates a generic Out-of-Stock Exception.
     */
    public KingGenReachedLimitException() {
        super("You reached your daily Alt limit!");
    }
}
