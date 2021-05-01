package ga.geist.jrv.auth;

/**
 * An authorization stragegy.
 */
public interface AuthStrategy {
    /**
     * Get the current session token
     * 
     * @return The current session token
     */
    public String getSessionToken();

    /**
     * Get the current session ID
     * 
     * @return The current session ID
     */
    public String getSessionId();

    /**
     * Get the current user ID
     * 
     * @return The current user ID
     */
    public String getUserId();
}
