package ga.geist.jrv.packets.clientbound;

import org.json.JSONObject;

import ga.geist.jrv.SocketConnector;
import ga.geist.jrv.events.MessageEvent;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.types.Message;

/**
 * Message packet
 */
public class MessagePacket implements ClientboundPacket {
    @Override
    public void pass(String stringMessage, SocketConnector client) {
        Message message = Message.fromJSON(new JSONObject(stringMessage), client.getBridge());

        client.getBridge().getRegistries().getMessageRegistry().add(message.getId(), message);
        client.getBridge().dispatch(new MessageEvent(message));
    }
}
