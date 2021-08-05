package ga.geist.jrv.types;

import ga.geist.jrv.RevoltBridge;

/**
 * An object that stores a map of system message channels to their ID.
 */
public class SystemMessageMap {
    private RevoltBridge bridge;

    private String userLeft;
    private String userJoined;
    private String userBanned;
    private String userKicked;

    /**
     * Retrieve the channel that receives user ban notifications
     * 
     * @return The channel
     */
    public Channel getUserBanned() {
        return bridge.getRegistries().getChannelRegistry().get(userBanned);
    }

    /**
     * Retrieve the channel that receives user join notifications
     * 
     * @return The channel
     */
    public Channel getUserJoined() {
        return bridge.getRegistries().getChannelRegistry().get(userJoined);
    }

    /**
     * Retrieve the channel that receives user kick notifications
     * 
     * @return The channel
     */
    public Channel getUserKicked() {
        return bridge.getRegistries().getChannelRegistry().get(userKicked);
    }

    /**
     * Retrieve the channel that receives user leave notifications
     * 
     * @return The channel
     */
    public Channel getUserLeft() {
        return bridge.getRegistries().getChannelRegistry().get(userLeft);
    }

    /**
     * Set the channel that receives user ban notifications
     * 
     * @param userBanned The channel
     */
    public void setUserBanned(Channel userBanned) {
        this.userBanned = userBanned.getId();
    }

    /**
     * Set the channel that receives user join notifications
     * 
     * @param userJoined The channel
     */
    public void setUserJoined(Channel userJoined) {
        this.userJoined = userJoined.getId();
    }

    /**
     * Set the channel that receives user kick notifications
     * 
     * @param userKicked The channel
     */
    public void setUserKicked(Channel userKicked) {
        this.userKicked = userKicked.getId();
    }

    /**
     * Set the channel that receives user leave notifications
     * 
     * @param userLeft The channel
     */
    public void setUserLeft(Channel userLeft) {
        this.userLeft = userLeft.getId();
    }

    /**
     * Construct a new system message channel map.
     * 
     * @param userBanned User ban notification channel
     * @param userJoined User join notification channel
     * @param userKicked User kick notification channel
     * @param userLeft   User leave notification channel
     * @param bridge     Revolt bridge
     */
    public SystemMessageMap(Channel userBanned, Channel userJoined, Channel userKicked, Channel userLeft,
            RevoltBridge bridge) {
        this.bridge = bridge;
        if (userBanned != null)
            this.userBanned = userBanned.getId();
        if (userJoined != null)
            this.userJoined = userJoined.getId();
        if (userKicked != null)
            this.userKicked = userKicked.getId();
        if (userLeft != null)
            this.userLeft = userLeft.getId();
    }
}
