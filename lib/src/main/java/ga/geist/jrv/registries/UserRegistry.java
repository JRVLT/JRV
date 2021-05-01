package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.types.User;

/**
 * Stores users.
 */
public class UserRegistry {
    private final Map<String, User> users = new HashMap<>();

    /**
     * Get the user map
     * 
     * @return The user map
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /**
     * Add an item to the user map
     * 
     * @param id   User ID
     * @param user User Class
     */
    public void add(String id, User user) {
        users.put(id, user);
    }

    /**
     * Check if the user map has the ID
     * 
     * @param id The ID to check against
     * @return boolean value: is the user already registered
     */
    public boolean has(String id) {
        return users.containsKey(id);
    }

    /**
     * Get a user by ID
     * 
     * @param id The ID of the user
     * @return The user <em><strong>(or <code>null</code>, if the user is not
     *         found)</strong></em>
     */
    public User get(String id) {
        if (!has(id))
            return null;
        return users.get(id);
    }
}
