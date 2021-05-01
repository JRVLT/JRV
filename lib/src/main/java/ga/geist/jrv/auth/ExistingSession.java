package ga.geist.jrv.auth;

/**
 * Used to log in with an existing session, using session token, session ID and
 * user ID grabbed from elsewhere.
 */
public class ExistingSession implements AuthStrategy {
    private String sessionId;
    private String sessionToken;
    private String userId;

    /**
     * Get the session ID
     * 
     * @return The ID of the session
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Get the session token
     * 
     * @return The token of the session
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Get the user ID
     * 
     * @return The ID of the user in the session
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Construct a new session.
     * 
     * @param sessionId    Session ID
     * @param sessionToken Session Token
     * @param userId       User ID
     */
    public ExistingSession(String sessionId, String sessionToken, String userId) {
        this.sessionId = sessionId;
        this.sessionToken = sessionToken;
        this.userId = userId;
    }
}
