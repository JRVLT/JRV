package ga.geist.jrv.packets;

import org.java_websocket.client.WebSocketClient;

/**
 * Clientbound (i.e. S2C) packet interface.
 */
public interface ClientboundPacket {
    /**
     * Pass event.
     * 
     * @param stringMessage Raw message (as a string)
     * @param client        WebSocket Client
     */
    public void pass(String stringMessage, WebSocketClient client);
}
