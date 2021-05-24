package ga.geist.jrv.registries;

import java.util.LinkedHashMap;
import java.util.Map;

import ga.geist.jrv.types.Message;

/**
 * Registry for Messages
 */
public class MessageRegistry {
    private final Map<String, Message> messages = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Message> eldest) {
            return size() > 500;
        }
    };

    /**
     * Get the map of messages
     * 
     * @return Messages map
     */
    public Map<String, Message> getMessages() {
        return messages;
    }

    /**
     * Add a message to the map
     * 
     * @param id  ID of the message
     * @param msg Message instance
     */
    public void add(String id, Message msg) {
        messages.put(id, msg);
    }

    /**
     * Check if a message is inside the map
     * 
     * @param id ID of the message
     * @return A boolean indicating presence
     */
    public boolean has(String id) {
        return messages.containsKey(id);
    }

    /**
     * Get a message by ID
     * 
     * @param id ID of the message
     * @return The message (or null)
     */
    public Message get(String id) {
        if (!has(id))
            return null;
        return messages.get(id);
    }
}
