package ga.geist.jrv.packets.serverbound;

import org.json.JSONObject;

import ga.geist.jrv.packets.ServerboundPacket;

/**
 * Authenticate packet.
 */
public class AuthenticatePacket implements ServerboundPacket {
    private String sessionId;
    private String sessionKey;
    private String userId;

    /**
     * Construct a new Authenticate packet
     * 
     * @param sessionId  Session ID
     * @param sessionKey Session Key
     * @param userId     User ID
     */
    public AuthenticatePacket(String sessionId, String sessionKey, String userId) {
        this.sessionId = sessionId;
        this.sessionKey = sessionKey;
        this.userId = userId;
    }

    /**
     * Retrieve session ID
     * 
     * @return The session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Retrieve sesion key
     * 
     * @return The session key
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * Get user ID
     * 
     * @return The user ID
     */
    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        JSONObject packet = new JSONObject();
        packet.put("type", "Authenticate");
        packet.put("id", this.sessionId);
        packet.put("user_id", this.userId);
        packet.put("session_token", this.sessionKey);

        return packet.toString();
    }
}
