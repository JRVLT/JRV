package ga.geist.jrv.packets.serverbound;

import org.json.JSONObject;

import ga.geist.jrv.packets.ServerboundPacket;

/**
 * Authenticate packet.
 */
public class AuthenticatePacket implements ServerboundPacket {
    private String token;

    /**
     * Construct a new Authenticate packet
     * 
     * @param token Token
     */
    public AuthenticatePacket(String token) {
        this.token = token;
    }

    /**
     * Retrieve bot token
     * 
     * @return The bot token
     */
    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        JSONObject packet = new JSONObject();
        packet.put("type", "Authenticate");
        packet.put("token", this.token);

        return packet.toString();
    }
}
