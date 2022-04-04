package wtf.kinggen.exceptions;

/**
 * Exception for an Invalid response from the Server.
 */
public class KingGenInvalidResponseException extends Exception {

    /**
     * Generates a generic Invalid-Response Exception.
     */
    public KingGenInvalidResponseException() {
        super("The Response from the Server couldn't be validated!");
    }

    /**
     * Generates a generic Invalid-Response Exception, but with the content of the response.
     * @param content the response.
     */
    public KingGenInvalidResponseException(String content) {
        super("The Response from the Server couldn't be validated!\nResponse: " + content);
    }

}
