package ga.geist.jrv.registries;

import java.util.HashMap;
import java.util.Map;

import ga.geist.jrv.types.GroupDM;

/**
 * Registry for Group DMs
 */
public class GDMRegistry {
    private final Map<String, GroupDM> groupDMs = new HashMap<>();

    /**
     * Get the map of GDMs
     * 
     * @return GDM map
     */
    public Map<String, GroupDM> getGroupDMs() {
        return groupDMs;
    }

    /**
     * Add a GDM to the map
     * 
     * @param id  ID of the GDM
     * @param gdm GDM instance
     */
    public void add(String id, GroupDM gdm) {
        groupDMs.put(id, gdm);
    }

    /**
     * Check if a GDM is inside the map
     * 
     * @param id ID of the GDM
     * @return A boolean indicating presence
     */
    public boolean has(String id) {
        return groupDMs.containsKey(id);
    }

    /**
     * Get a GDM by ID
     * 
     * @param id ID of the GDM
     * @return The GDM (or null)
     */
    public GroupDM get(String id) {
        if (!has(id))
            return null;
        return groupDMs.get(id);
    }
}
