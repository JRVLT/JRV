package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.packets.ClientboundPacket;

/**
 * Registry of client packets.
 */
public class ClientPacketRegistry {
    private final Map<String, ClientboundPacket> registry = new HashMap<>();

    /**
     * Construct an instance. This method includes pre-made definitions of already
     * included packets.
     */
    public ClientPacketRegistry() {
        //
    }

    /**
     * Return the packet map.
     * 
     * @return The packet map.
     */
    public Map<String, ClientboundPacket> getRegistry() {
        return registry;
    }

    /**
     * Add a packet to the map
     * 
     * @param type   "Type" identifier
     * @param packet C2S packet class instance
     */
    public void add(String type, ClientboundPacket packet) {
        registry.put(type, packet);
    }

    /**
     * Get a packet by name
     * 
     * @param name Name of the packet
     * @return C2S packet class instance (or null)
     */
    public ClientboundPacket getByName(String name) {
        if (!registry.keySet().contains(name))
            return null;

        return registry.get(name);
    }
}
