package ga.geist.jrv;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ga.geist.jrv.auth.AuthStrategy;
import ga.geist.jrv.events.Event;
import ga.geist.jrv.packets.serverbound.AuthenticatePacket;
import ga.geist.jrv.registries.RegistryRegistry;
import ga.geist.jrv.utils.RestUtils;

/**
 * Main class for JRV.
 */
public class RevoltBridge {
    private URI restUrl;
    private List<RevoltEventListener> eventListeners = new ArrayList<>();
    private RegistryRegistry registries = new RegistryRegistry();
    private SocketConnector connector;

    private String sessionToken;
    private String sessionId;
    private String selfUserID;

    private JSONObject instanceRoot;

    /**
     * Create a new RevoltBridge instance
     * 
     * @param restUrl URL of the REST API (e.g. https://api.revolt.chat)
     * @throws URISyntaxException If the URI is malformed
     */
    public RevoltBridge(URI restUrl) throws URISyntaxException {
        this.restUrl = restUrl;
        this.instanceRoot = new JSONObject(RestUtils.getJson(restUrl));
        this.connector = new SocketConnector(new URI(this.instanceRoot.getString("ws")), this);

        this.connector.connect();
    }

    /**
     * Retrieve the active REST url of the instance
     * 
     * @return The REST URL
     */
    public URI getRestUrl() {
        return restUrl;
    }

    /**
     * Retrieve the active WebSocket connector of the instance
     * 
     * @return The WebSocket connector
     */
    public SocketConnector getConnector() {
        return connector;
    }

    /**
     * Retrieve the active session.
     * 
     * @return The active session token
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Retrieve the active session token
     * 
     * @return The active session token
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Retrieve the active user ID
     * 
     * @return The active user ID
     */
    public String getSelfUserId() {
        return selfUserID;
    }

    /**
     * Retrieve the instance root (the instance information available at the / root
     * path)
     * 
     * @return The instance root as stringified JSON with newlines and 2-space
     *         indent
     */
    public String getInstanceRoot() {
        return instanceRoot.toString(2);
    }

    /**
     * Register an additional event listener
     * 
     * @param listener The listener to register
     */
    public void registerEventListener(RevoltEventListener listener) {
        this.eventListeners.add(listener);
    }

    /**
     * Dispatch an event
     * 
     * @param event Event
     */
    public void dispatch(Event event) {
        for (RevoltEventListener revoltEventListener : eventListeners) {
            revoltEventListener.onEvent(event, this);
        }
    }

    /**
     * Get available registries for channels/users/...
     * 
     * @return The registries
     */
    public RegistryRegistry getRegistries() {
        return registries;
    }

    /**
     * Authenticate via an AuthStrategy
     * 
     * @param strategy AuthStrategy (for example, ExistingSession)
     */
    public void authenticate(AuthStrategy strategy) {
        this.sessionId = strategy.getSessionId();
        this.sessionToken = strategy.getSessionToken();
        this.selfUserID = strategy.getUserId();

        AuthenticatePacket authPacket = new AuthenticatePacket(this.sessionId, this.sessionToken, this.selfUserID);

        this.connector.send(authPacket.toString());
    }
}
