package ga.geist.jrv.events;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Raw Event — Fired on every clientbound message
 */
public class RawEvent extends Event {
    private JSONObject message;

    /**
     * Construct a new RawEvent with a JSONObject
     * 
     * @param message Message
     */
    public RawEvent(JSONObject message) {
        this.message = message;
    }

    /**
     * Construct a new RawEvent with a JSON string
     * 
     * @param message Message, JSON in a string
     * @throws JSONException If <code>message</code> is not valid parse-able JSON.
     */
    public RawEvent(String message) throws JSONException {
        this.message = new JSONObject(message);
    }

    /**
     * Get message
     * 
     * @return The message as stringified JSON with newlines and 2-space indent
     */
    public String getMessage() {
        return message.toString(2);
    }
}
