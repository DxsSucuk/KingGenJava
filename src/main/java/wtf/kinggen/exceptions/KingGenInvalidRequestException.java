package wtf.kinggen.exceptions;

/**
 * Exception for an Invalid Request send my the Wrapper.
 */
public class KingGenInvalidRequestException extends Exception {

    /**
     * Generates a generic Invalid-Request Exception.
     */
    public KingGenInvalidRequestException() {
        super("The created Request is not valid.");
    }

    /**
     * Generates a generic Invalid-Request Exception.
     *
     * @param reason The Reason of why it is Invalid.
     */
    public KingGenInvalidRequestException(String reason) {
        super("The created Request is not valid. Reason for this is, " + reason);
    }

}
