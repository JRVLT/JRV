package ga.geist.jrv.types;

import ga.geist.jrv.RevoltBridge;
import ga.geist.jrv.cores.MessageCore;

/**
 * Generic channel class (usually extended)
 */
public class Channel {
    private String type;
    private String id;
    private RevoltBridge bridge;

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
     * Send a messsage
     * 
     * @param contents Message contents as a string
     */
    public void sendMessage(String contents) {
        MessageCore.sendMessage(this.getId(), contents, this.bridge);
    }

    /**
     * Construct a channel
     * 
     * @param type   Channel type
     * @param id     Channel ID
     * @param bridge Revolt bridge
     */
    public Channel(String type, String id, RevoltBridge bridge) {
        this.type = type;
        this.id = id;
        this.bridge = bridge;
    }
}
