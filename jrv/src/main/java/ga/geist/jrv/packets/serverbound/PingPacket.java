package ga.geist.jrv.packets.serverbound;

import java.util.Date;

import org.json.JSONObject;

import ga.geist.jrv.packets.ServerboundPacket;

/**
 * Ping packet.
 */
public class PingPacket implements ServerboundPacket {
    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("type", "Ping");
        obj.put("time", new Date().getTime());

        return obj.toString();
    }
}
