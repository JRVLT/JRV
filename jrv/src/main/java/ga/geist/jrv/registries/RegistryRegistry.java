package ga.geist.jrv.registries;

// Not only is this class very meta,
// unlike all other registries it stores all of the registries in a __fixed__ way.
// Registries cannot be added or removed to/from the RegistryRegistry at runtime.
// Instead this is done at compile-time.
// Please keep in mind to update this class when adding a registry.
// The ClientPacketRegistry does not need to be managed in the RegistryRegistry.

/**
 * Registry of registries
 */
public class RegistryRegistry {
    private GDMRegistry gdmRegistry;
    private UserRegistry userRegistry;
    private DirectMessageRegistry dmRegistry;
    private ChannelRegistry channelRegistry;
    private MessageRegistry messageRegistry;

    /**
     * Get registry of group DMs
     * 
     * @return Registry of group DMs
     */
    public GDMRegistry getGdmRegistry() {
        return gdmRegistry;
    }

    /**
     * Get registry of users
     * 
     * @return Registry of users
     */
    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    /**
     * Get registry of DMs
     * 
     * @return Registry of DMs
     */
    public DirectMessageRegistry getDmRegistry() {
        return dmRegistry;
    }

    /**
     * Get registry of channels
     * 
     * @return Registry of channels
     */
    public ChannelRegistry getChannelRegistry() {
        return channelRegistry;
    }

    /**
     * Get registry of messages
     * 
     * @return Registry of messages
     */
    public MessageRegistry getMessageRegistry() {
        return messageRegistry;
    }

    /**
     * Construct an instance
     */
    public RegistryRegistry() {
        this.gdmRegistry = new GDMRegistry();
        this.userRegistry = new UserRegistry();
        this.dmRegistry = new DirectMessageRegistry();
        this.channelRegistry = new ChannelRegistry();
        this.messageRegistry = new MessageRegistry();
    }
}
