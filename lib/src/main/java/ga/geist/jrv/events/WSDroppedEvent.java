package ga.geist.jrv.events;

/**
 * WS Dropped Event — Fired when the WS connection closes
 */
public class WSDroppedEvent extends Event {
    private int code;
    private String reason;
    private boolean remote;

    /**
     * Retrieves the status code for the WS connection closure
     * 
     * @return Status code for the WS connection closure
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the (string) reason for the WS connection closure
     * 
     * @return Reason for the WS connection closure
     */
    public String getReason() {
        return reason;
    }

    /**
     * Indicates if the connection was closed by the server
     * 
     * @return A boolean, true if the connection closure was caused by the server
     */
    public boolean isRemote() {
        return remote;
    }

    /**
     * Construct a new WSDroppedEvent
     * 
     * @param code   Code
     * @param reason Reason
     * @param remote Remote indicator boolean
     */
    public WSDroppedEvent(int code, String reason, boolean remote) {
        this.code = code;
        this.reason = reason;
        this.remote = remote;
    }
}
