package ga.geist.jrv.cores;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;
import ga.geist.jrv.types.Attachment;
import ga.geist.jrv.types.Channel;
import ga.geist.jrv.types.Message;
import ga.geist.jrv.types.User;
import ga.geist.jrv.utils.AuthenticatedRestUtils;

/**
 * Utility classes for messages, mostly used in the library itself. If you are
 * using this in your own code, you're probably looking for something else,
 * although there are valid uses, so do not be discouraged to use the
 * MessageCore directly.
 */
public class MessageCore {
    private MessageCore() {
    }

    /**
     * Send a message
     * 
     * @param channelId Channel ID
     * @param contents  Message contents
     * @param bridge    Revolt bridge
     */
    public static void sendMessage(String channelId, String contents, RevoltBridge bridge) {
        JSONObject body = new JSONObject();
        body.put("content", contents);
        body.put("nonce", makeNonce());

        AuthenticatedRestUtils.postJson(bridge.getRestUrl().resolve(String.format("/channels/%s/messages", channelId)),
                body, bridge.getSelfUserId(), bridge.getSessionToken());
    }

    /**
     * Generate a nonce
     * 
     * @return A nonce, currently the first 18 characters of a UUID prefixed with
     *         "JRV-MSG-"
     */
    public static String makeNonce() {
        UUID uuid = UUID.randomUUID();
        return "JRV-MSG-" + uuid.toString().substring(0, 18);
    }

    /**
     * Fetch 50 messages from a channel
     * 
     * @param channelId ID of the channel to fetch from
     * @param before    Fetch messages older than the message specified in "before".
     * @return An array of 50 (or less) messages.
     */
    public static Message[] fetchMessages(String channelId, Message before, RevoltBridge bridge) {
        boolean hasBefore = before == null;
        URI url = bridge.getRestUrl().resolve(hasBefore ? String.format("/channels/%s/messages", channelId)
                : String.format("/channels/%s/messages?before=%s", channelId, before.getId()));

        JSONArray messageArray = new JSONArray(
                AuthenticatedRestUtils.getJson(url, bridge.getSelfUserId(), bridge.getSessionToken()));
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < messageArray.length(); i++) {
            JSONObject messageObject = messageArray.getJSONObject(i);

            User author = bridge.getRegistries().getUserRegistry().get(messageObject.getString("author"));
            Channel channel = bridge.getRegistries().getChannelRegistry().get(messageObject.getString("channel"));
            Attachment attachment = Attachment.fromJSON(messageObject.getJSONObject("attachment"));

            Message message = new Message(author, channel, messageObject.getString("_id"),
                    messageObject.getString("nonce"), messageObject.getString("content"), attachment);

            messages.add(message);
        }

        return messages.toArray(new Message[0]);
    }
}
