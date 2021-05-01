package ga.geist.jrv.types;

/**
 * Generic channel class (usually extended)
 */
public class Channel {
    private String type;
    private String id;

    /**
     * Get the type of a channel
     * 
     * @return Type string
     */
    public String getType() {
        return type;
    }

    /**
     * Get the ID of a channel
     * 
     * @return ID string
     */
    public String getId() {
        return id;
    }

    /**
     * Construct a channel
     * 
     * @param type Channel type
     * @param id   Channel ID
     */
    public Channel(String type, String id) {
        this.type = type;
        this.id = id;
    }
}
