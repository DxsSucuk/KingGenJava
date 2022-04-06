package wtf.kinggen.exceptions;

/**
 * Exception for an Operation or Parsing Exception.
 */
public class KingGenInvalidOperationException extends NullPointerException {

    /**
     * Generates a generic Invalid-Operation Exception.
     */
    public KingGenInvalidOperationException() {
        super("The created Request is not valid.");
    }

    /**
     * Generates a generic Invalid-Operation Exception, but with the reason of the exception.
     * @param content the reason.
     */
    public KingGenInvalidOperationException(String content) {
        super(content);
    }

}
