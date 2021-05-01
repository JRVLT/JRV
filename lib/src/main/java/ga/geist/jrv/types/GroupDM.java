package ga.geist.jrv.types;

import java.util.List;

import org.json.JSONObject;

/**
 * Group DM: DM with multiple recipients
 */
public class GroupDM extends Channel {
    private String nonce;
    private String name;
    private String owner;
    private String description;
    private String[] recipients;
    private ShortMessage shortMessage;

    /**
     * Get the nonce of a GDM
     * 
     * @return The nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * Get the name of a GDM
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the owner of a GDM
     * 
     * @return The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Get the description of a GDM
     * 
     * @return The description (unparsed markdown)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the recipients of a GDM
     * 
     * @return The recipients (array of IDs)
     */
    public String[] getRecipients() {
        return recipients;
    }

    /**
     * Get the latest message
     * 
     * @return The latest message as a ShortMessage
     */
    public ShortMessage getShortMessage() {
        return shortMessage;
    }

    /**
     * Construct a new instance
     * 
     * @param type         Type
     * @param id           ID
     * @param nonce        Server-generated nonce
     * @param name         Name
     * @param owner        Group owner
     * @param description  Description
     * @param recipients   Recipient array
     * @param shortMessage Last message as ShortMessage
     */
    public GroupDM(String type, String id, String nonce, String name, String owner, String description,
            String[] recipients, ShortMessage shortMessage) {
        super(type, id);
        this.nonce = nonce;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.recipients = recipients;
        this.shortMessage = shortMessage;
    }

    /**
     * Create a GDM from Revolt API JSON
     * 
     * @param object JSON object
     * @return New instance with data from the JSON object
     */
    public static GroupDM fromJSON(JSONObject object) {
        JSONObject msgObject;

        if (object.optJSONObject("shortMessage") == null) {
            msgObject = new JSONObject();
        } else {
            msgObject = object.optJSONObject("shortMessage");
        }

        ShortMessage message = new ShortMessage(msgObject.optString("_id"), msgObject.optString("author"),
                msgObject.optString("short"));

        List<Object> recipientList = object.optJSONArray("recipients").toList();

        return new GroupDM(object.optString("channel_type"), object.optString("_id"), object.optString("nonce"),
                object.optString("name"), object.optString("owner"), object.optString("description"),
                recipientList.toArray(new String[recipientList.size()]), message);
    }
}
