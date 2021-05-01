package ga.geist.jrv.types;

import org.json.JSONObject;

/**
 * "Saved Messages" self-DM
 */
public class SavedMessageDM extends Channel {
    private String user;

    /**
     * Get the user
     * 
     * @return Get the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Construct a new SavedMessage
     * 
     * @param type Type
     * @param id   Channel ID
     * @param user User ID
     */
    public SavedMessageDM(String type, String id, String user) {
        super(type, id);
        this.user = user;
    }

    /**
     * Create a saved message DM from Revolt API JSON
     * 
     * @param object JSON object
     * @return New instance with data from the JSON object
     */
    public static SavedMessageDM fromJSON(JSONObject object) {
        return new SavedMessageDM(object.optString("channel_type"), object.optString("_id"), object.optString("user"));
    }
}
