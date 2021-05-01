package ga.geist.jrv.types;

import org.json.JSONObject;

/**
 * Contains a user.
 */
public class User {
    private String id;
    private String username;
    private int badges;
    private String presence;
    private String status;
    private String relationship;
    private boolean online;

    /**
     * Get the badges of the user
     * 
     * @return Badges bitfield
     */
    public int getBadges() {
        return badges;
    }

    /**
     * Get the ID of the user
     * 
     * @return ID (likely in ULID format)
     */
    public String getId() {
        return id;
    }

    /**
     * Get the presence of the user
     * 
     * @return The presence string
     */
    public String getPresence() {
        return presence;
    }

    /**
     * Get the relationship with a user
     * 
     * @return The relationship string
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Get the status of the user
     * 
     * @return The status string
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the username of the user
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the online/offline status of the user
     * 
     * @return The boolean online/offline status of the user
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Construct a user
     * 
     * @param id           User ID
     * @param username     Username
     * @param badges       Badges bitfield
     * @param presence     Presence string
     * @param relationship Relationship string
     * @param status       Status string
     * @param online       Online
     */
    public User(String id, String username, int badges, String presence, String relationship, String status,
            boolean online) {
        this.id = id;
        this.username = username;
        this.badges = badges;
        this.presence = presence;
        this.relationship = relationship;
        this.status = status;
        this.online = online;
    }

    /**
     * Create a user from Revolt API JSON
     * 
     * @param object JSON Object
     * @return User instance
     */
    public static User fromJSON(JSONObject object) {
        JSONObject status;

        if (object.optJSONObject("status") == null) {
            status = new JSONObject();
        } else {
            status = object.getJSONObject("status");
        }

        return new User(object.optString("_id"), object.optString("username"), object.optInt("badges"),
                status.optString("presence"), object.optString("relationship"), status.optString("text"),
                object.optBoolean("online"));
    }
}
