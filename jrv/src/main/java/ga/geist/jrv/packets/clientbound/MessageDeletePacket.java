package ga.geist.jrv.packets.clientbound;

import ga.geist.jrv.events.ChannelTypingStartEvent;
import ga.geist.jrv.events.MessageDeleteEvent;
import ga.geist.jrv.types.Channel;
import ga.geist.jrv.types.User;
import org.json.JSONObject;

import ga.geist.jrv.SocketConnector;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.types.Message;
import ga.geist.jrv.events.MessageUpdateEvent;

/**
 * MessageUpdate packet
 */
public class MessageDeletePacket implements ClientboundPacket {
    @Override
    public void pass(String stringMessage, SocketConnector client) {
        JSONObject messageObject = new JSONObject(stringMessage);
        String messageId = messageObject.optString("id");
        String channelId = messageObject.optString("channel");

        Channel channel = client.getBridge().getRegistries().getChannelRegistry().get(channelId);
        Message message = client.getBridge().getRegistries().getMessageRegistry().get(messageId);

        client.getBridge().dispatch(new MessageDeleteEvent(message, channel));
    }
}
