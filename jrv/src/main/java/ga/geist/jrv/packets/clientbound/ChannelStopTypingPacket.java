package ga.geist.jrv.packets.clientbound;

import org.json.JSONObject;

import ga.geist.jrv.SocketConnector;
import ga.geist.jrv.events.ChannelTypingStopEvent;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.types.Channel;
import ga.geist.jrv.types.User;

/**
 * ChannelStopTyping packet
 */
public class ChannelStopTypingPacket implements ClientboundPacket {
    @Override
    public void pass(String stringMessage, SocketConnector client) {
        JSONObject messageObject = new JSONObject(stringMessage);
        String channelId = messageObject.optString("id");
        String userId = messageObject.optString("user");

        Channel channel = client.getBridge().getRegistries().getChannelRegistry().get(channelId);
        User user = client.getBridge().getRegistries().getUserRegistry().get(userId);

        client.getBridge().dispatch(new ChannelTypingStopEvent(channel, user));
    }
}
