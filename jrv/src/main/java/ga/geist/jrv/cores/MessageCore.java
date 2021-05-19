package ga.geist.jrv.cores;

import java.util.UUID;

import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;
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
}
