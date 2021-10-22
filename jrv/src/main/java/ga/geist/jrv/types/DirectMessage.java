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
     * Construct a direct message
     * 
     * @param type       Type
     * @param id         ID
     * @param active     Active
     * @param recipients Recipient array
     * @param bridge     Revolt bridge
     */
    public DirectMessage(String type, String id, boolean active, String[] recipients, RevoltBridge bridge) {
        super(type, id, bridge);
        this.active = active;
        this.recipients = recipients;
    }

    /**
     * Create a direct message from Revolt API JSON
     * 
     * @param object JSON object
     * @param bridge Revolt bridge
     * @return New instance with data from the JSON object
     */
    public static DirectMessage fromJSON(JSONObject object, RevoltBridge bridge) {
        List<Object> recipientList = object.optJSONArray("recipients").toList();

        return new DirectMessage(object.optString("channel_type"), object.optString("_id"), object.optBoolean("active"),
                recipientList.toArray(new String[recipientList.size()]), bridge);
    }
}
