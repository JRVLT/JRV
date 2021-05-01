package ga.geist.jrv.events;

/**
 * WebSocket Error Event — Fired when the WebSocket connection encounters an
 * error
 */
public class WSErrorEvent extends Event {
    private Exception exception;

    /**
     * Construct an instance
     * 
     * @param ex An exception
     */
    public WSErrorEvent(Exception ex) {
        this.exception = ex;
    }

    /**
     * Retrieve the exception
     * 
     * @return The exception
     */
    public Exception getException() {
        return exception;
    }
}
