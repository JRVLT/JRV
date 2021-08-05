package ga.geist.jrv.types;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;
import ga.geist.jrv.registries.RegistryRegistry;

/**
 * An object representing a server.
 */
public class Server {
    private RevoltBridge bridge;
    private String owner;
    private String[] channels;
    private SystemMessageMap systemMessageMap;
    private String name;
    private String description;
    private Attachment icon;
    private Attachment banner;
    private String id;
    private String nonce;

    /**
     * Retrieve the owner
     * 
     * @return The owner of the server
     */
    public User getOwner() {
        return bridge.getRegistries().getUserRegistry().get(owner);
    }

    /**
     * Retrieve the channels
     * 
     * @return The array of channels
     */
    public Channel[] getChannels() {
        List<Channel> channelList = new ArrayList<>();

        for (int i = 0; i < this.channels.length; i++) {
            Channel channel = bridge.getRegistries().getChannelRegistry().get(this.channels[i]);

            if (channel == null)
                continue;

            channelList.add(channel);
        }

        return channelList.toArray(new Channel[0]);
    }

    /**
     * Retrieve the system message channels
     * 
     * @return The system message map of channels
     */
    public SystemMessageMap getSystemMessageMap() {
        return systemMessageMap;
    }

    /**
     * Retrieve the name of the server
     * 
     * @return The name of the server
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the description of the server
     * 
     * @return The description of the server
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieve the icon of the server
     * 
     * @return The icon of the server
     */
    public Attachment getIcon() {
        return icon;
    }

    /**
     * Retrieve the banner of the server
     * 
     * @return The banner of the server
     */
    public Attachment getBanner() {
        return banner;
    }

    /**
     * Retrieve the ID of the server
     * 
     * @return The ID of the server
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieve the nonce of the server
     * 
     * @return The nonce of the server
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * Construct a new instance of Server
     * 
     * @param owner            Server owner
     * @param channels         Server channels
     * @param systemMessageMap Servr
     * @param name             Server name
     * @param description      Server description
     * @param icon             Server icon
     * @param banner           Server banner
     * @param id               Server ID
     * @param nonce            Server nonce
     * @param bridge           Revolt bridge
     */
    public Server(User owner, Channel[] channels, SystemMessageMap systemMessageMap, String name, String description,
            Attachment icon, Attachment banner, String id, String nonce, RevoltBridge bridge) {
        this.owner = owner.getId();
        this.systemMessageMap = systemMessageMap;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.banner = banner;
        this.id = id;
        this.nonce = nonce;
        this.bridge = bridge;

        // This turns Channel[] into a String[] with channel IDs
        List<String> idList = new ArrayList<>();
        for (Channel channel : channels) {
            idList.add(channel.getId());
        }
        this.channels = idList.toArray(new String[0]);
    }

    /**
     * Construct a new instance of Server from a JSON object
     * 
     * @param object The JSON object
     * @param bridge Revolt bridge
     * @return The new Server instance
     */
    public static Server fromJSON(JSONObject object, RevoltBridge bridge) {
        RegistryRegistry registries = bridge.getRegistries();

        User owner = registries.getUserRegistry().get(object.getString("owner"));

        JSONArray jsonChannelIDs = object.getJSONArray("channels");
        List<Channel> channelList = new ArrayList<>();
        for (int i = 0; i < jsonChannelIDs.length(); i++) {
            Channel channel = registries.getChannelRegistry().get(jsonChannelIDs.getString(i));

            if (channel == null)
                continue;

            channelList.add(channel);
        }

        JSONObject jsonSystemMessages = object.getJSONObject("system_messages");
        SystemMessageMap messageMap = new SystemMessageMap(
                registries.getChannelRegistry().get(jsonSystemMessages.optString("user_banned")),
                registries.getChannelRegistry().get(jsonSystemMessages.optString("user_joined")),
                registries.getChannelRegistry().get(jsonSystemMessages.optString("user_kicked")),
                registries.getChannelRegistry().get(jsonSystemMessages.optString("user_left")), bridge);

        Attachment banner = null;
        if (object.has("banner"))
            banner = Attachment.fromJSON(object.getJSONObject("banner"));

        Attachment icon = null;
        if (object.has("icon"))
            icon = Attachment.fromJSON(object.getJSONObject("icon"));

        String description;
        if (object.has("description"))
            description = object.getString("description");
        else
            description = "";

        return new Server(owner, channelList.toArray(new Channel[0]), messageMap, object.getString("name"), description,
                icon, banner, object.getString("_id"), object.getString("nonce"), bridge);
    }
}
