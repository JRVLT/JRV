package ga.geist.jrv.types;

import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;

/**
 * "Saved Messages" self-DM
 */
public class SavedMessageDM extends Channel {
    private String user;
    private RevoltBridge bridge;

    /**
     * Get the user
     * 
     * @return Get the user
     */
    public User getUser() {
        return bridge.getRegistries().getUserRegistry().get(user);
    }

    /**
     * Construct a new SavedMessage
     * 
     * @param type   Type
     * @param id     Channel ID
     * @param user   User ID
     * @param bridge Revolt bridge
     */
    public SavedMessageDM(String type, String id, String user, RevoltBridge bridge) {
        super(type, id, bridge);
        this.user = user;
        this.bridge = bridge;
    }

    /**
     * Create a saved message DM from Revolt API JSON
     * 
     * @param object JSON object
     * @param bridge Revolt bridge
     * @return New instance with data from the JSON object
     */
    public static SavedMessageDM fromJSON(JSONObject object, RevoltBridge bridge) {
        return new SavedMessageDM(object.optString("channel_type"), object.optString("_id"), object.optString("user"),
                bridge);
    }
}
