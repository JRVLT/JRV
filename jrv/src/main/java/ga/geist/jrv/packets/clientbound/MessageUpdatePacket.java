package ga.geist.jrv.packets.clientbound;

import org.json.JSONObject;

import ga.geist.jrv.SocketConnector;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.types.Message;
import ga.geist.jrv.events.MessageUpdateEvent;

/**
 * MessageUpdate packet
 */
public class MessageUpdatePacket implements ClientboundPacket {
    @Override
    public void pass(String stringMessage, SocketConnector client) {
        JSONObject messageObject = new JSONObject(stringMessage);
        Message oldMessage = client.getBridge().getRegistries().getMessageRegistry().get(messageObject.getString("id"));
        JSONObject data = messageObject.optJSONObject("data");

        if (oldMessage == null || data == null) // Message is no longer cached or server returned no changes.
            return;

        boolean contentChanged = !data.optString("content").equals("");

        Message newMessage;

        if (contentChanged) {
            newMessage = new Message(oldMessage.getAuthor(), oldMessage.getChannel(), oldMessage.getId(),
                    oldMessage.getNonce(), data.optString("content"), oldMessage.getAttachment());

            client.getBridge().getRegistries().getMessageRegistry().add(messageObject.getString("id"), newMessage);
            client.getBridge().dispatch(new MessageUpdateEvent(oldMessage, newMessage));
        }
    }
}
