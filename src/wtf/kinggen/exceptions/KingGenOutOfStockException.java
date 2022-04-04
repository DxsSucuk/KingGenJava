package wtf.kinggen.exceptions;

/**
 * Exception for an Out-of-Stock response from the Server.
 */
public class KingGenOutOfStockException extends Exception {

    /**
     * Generates a generic Out-of-Stock Exception.
     */
    public KingGenOutOfStockException() {
        super("KingGen responded with an empty Account.");
    }

}
