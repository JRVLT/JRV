package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.types.Channel;

/**
 * Registry for channels.
 */
public class ChannelRegistry {
    private final Map<String, Channel> channels = new HashMap<>();

    /**
     * Get the channel map
     * 
     * @return The channel map
     */
    public Map<String, Channel> getChannels() {
        return channels;
    }

    /**
     * Add a channel to the map
     * 
     * @param id      Channel ID
     * @param channel Channel Class Instance
     */
    public void add(String id, Channel channel) {
        channels.put(id, channel);
    }

    /**
     * Check if a channel is inside the map
     * 
     * @param id ID of the channel
     * @return Boolean value indicating presence
     */
    public boolean has(String id) {
        return channels.containsKey(id);
    }

    /**
     * Get a channel by ID
     * 
     * @param id The target ID
     * @return The channel
     */
    public Channel get(String id) {
        if (!has(id))
            return null;
        return channels.get(id);
    }
}
