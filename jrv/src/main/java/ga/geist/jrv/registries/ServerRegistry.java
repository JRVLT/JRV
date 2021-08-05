package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.types.Server;

/**
 * Registry for servers.
 */
public class ServerRegistry {
    private final Map<String, Server> servers = new HashMap<>();

    /**
     * Get the server map
     * 
     * @return The server map
     */
    public Map<String, Server> getServers() {
        return servers;
    }

    /**
     * Add a server to the map
     * 
     * @param id     Server ID
     * @param server Server Class Instance
     */
    public void add(String id, Server server) {
        servers.put(id, server);
    }

    /**
     * Check if a server is inside the map
     * 
     * @param id ID of the server
     * @return Boolean value indicating presence
     */
    public boolean has(String id) {
        return servers.containsKey(id);
    }

    /**
     * Get a server by ID
     * 
     * @param id The target ID
     * @return The server
     */
    public Server get(String id) {
        if (!has(id))
            return null;
        return servers.get(id);
    }
}
