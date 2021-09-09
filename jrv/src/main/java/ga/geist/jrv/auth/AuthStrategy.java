package ga.geist.jrv.auth;

/**
 * An authorization stragegy.
 */
public interface AuthStrategy {
    /**
     * Get the bot token
     * 
     * @return The bot token
     */
    public String getBotToken();
}
