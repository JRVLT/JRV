package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.types.DirectMessage;

/**
 * Registry for Direct Messages
 */
public class DirectMessageRegistry {
    private final Map<String, DirectMessage> dms = new HashMap<>();

    /**
     * Get all group DMs
     * 
     * @return All group DMs
     */
    public Map<String, DirectMessage> getGroupDMs() {
        return dms;
    }

    /**
     * Add a DM to the map
     * 
     * @param id   ID of the DM
     * @param user DirectMessage instance
     */
    public void add(String id, DirectMessage user) {
        dms.put(id, user);
    }

    /**
     * Check if a channel is inside the map
     * 
     * @param id ID of the channel
     * @return A boolean indicating presence
     */
    public boolean has(String id) {
        return dms.containsKey(id);
    }

    /**
     * Get a channel by ID
     * 
     * @param id ID of the channel
     * @return The channel (or null)
     */
    public DirectMessage get(String id) {
        if (!has(id))
            return null;
        return dms.get(id);
    }
}
