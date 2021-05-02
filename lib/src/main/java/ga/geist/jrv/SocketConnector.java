package ga.geist.jrv;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import ga.geist.jrv.events.RawEvent;
import ga.geist.jrv.events.WSDroppedEvent;
import ga.geist.jrv.events.WSErrorEvent;
import ga.geist.jrv.events.WSOpenEvent;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.registries.ClientPacketRegistry;

/**
 * WebSocket connector
 */
public class SocketConnector extends WebSocketClient {
    private RevoltBridge bridge;

    SocketConnector(URI uri, RevoltBridge bridge) {
        super(uri);
        this.bridge = bridge;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.bridge.dispatch(new WSOpenEvent());
    }

    @Override
    public void onMessage(String message) {
        this.bridge.dispatch(new RawEvent(message));

        String messageType = new JSONObject(message).optString("type");

        if (messageType.equals(""))
            return;

        ClientPacketRegistry s2cPacketRegistry = this.bridge.getS2cPacketRegistry();
        ClientboundPacket packet = s2cPacketRegistry.getByName(messageType);

        if (packet == null)
            return;

        packet.pass(message, this);
    }

    @Override
    public void onError(Exception ex) {
        this.bridge.dispatch(new WSErrorEvent(ex));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        this.bridge.dispatch(new WSDroppedEvent(code, reason, remote));
    }

    /**
     * Get the active bridge
     * 
     * @return The bridge
     */
    public RevoltBridge getBridge() {
        return bridge;
    }
}
