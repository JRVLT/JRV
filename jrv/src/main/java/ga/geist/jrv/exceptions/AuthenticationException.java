package ga.geist.jrv.exceptions;

/**
 * Exception thrown when an error occurs with authentification
 */
public class AuthenticationException extends Exception {
    /**
     * Construct a new AuthenticationException
     * 
     * @param msg Message
     */
    public AuthenticationException(String msg) {
        super(msg);
    }
}
