package ga.geist.jrv.auth;

/**
 * Used to log in with an existing bot token.
 */
public class BotToken implements AuthStrategy {
    private String token;

    /**
     * Get the token
     * 
     * @return The token
     */
    public String getBotToken() {
        return token;
    }

    /**
     * Construct a new session.
     * 
     * @param token Token
     */
    public BotToken(String token) {
        this.token = token;
    }
}
