package ga.geist.jrv.types;

import java.util.List;

import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;

/**
 * Direct message class
 */
public class DirectMessage extends Channel {
    private boolean active;
    private String[] recipients;
    private ShortMessage lastMessage;

    /**
     * Get the active status of a DM (an inactive DM can also be referred to as a
     * hidden DM)
     * 
     * @return Active status as boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Get an array of recipients
     * 
     * @return The recipients (array of IDs)
     */
    public String[] getRecipients() {
        return recipients;
    }

    /**
     * Get the last message
     * 
     * @return The last message as a ShortMessage
     */
    public ShortMessage getLastMessage() {
        return lastMessage;
    }

    /**
     * Construct a direct message
     * 
     * @param type        Type
     * @param id          ID
     * @param active      Active
     * @param recipients  Recipient array
     * @param lastMessage Last message as ShortMessage
     * @param bridge      Revolt bridge
     */
    public DirectMessage(String type, String id, boolean active, String[] recipients, ShortMessage lastMessage,
            RevoltBridge bridge) {
        super(type, id, bridge);
        this.active = active;
        this.recipients = recipients;
        this.lastMessage = lastMessage;
    }

    /**
     * Create a direct message from Revolt API JSON
     * 
     * @param object JSON object
     * @param bridge Revolt bridge
     * @return New instance with data from the JSON object
     */
    public static DirectMessage fromJSON(JSONObject object, RevoltBridge bridge) {
        JSONObject msgObject;

        if (object.optJSONObject("shortMessage") == null) {
            msgObject = new JSONObject();
        } else {
            msgObject = object.optJSONObject("shortMessage");
        }

        ShortMessage message = new ShortMessage(msgObject.optString("_id"), msgObject.optString("author"),
                msgObject.optString("short"));

        List<Object> recipientList = object.optJSONArray("recipients").toList();

        return new DirectMessage(object.optString("channel_type"), object.optString("_id"), object.optBoolean("active"),
                recipientList.toArray(new String[recipientList.size()]), message, bridge);
    }
}
