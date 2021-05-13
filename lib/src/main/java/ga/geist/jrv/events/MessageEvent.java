package ga.geist.jrv.events;

import ga.geist.jrv.types.Message;

/**
 * Message Event — Fired when there is a new message
 */
public class MessageEvent extends Event {
    private Message message;

    /**
     * Construct a new MessageEvent
     * 
     * @param message Message
     */
    public MessageEvent(Message message) {
        this.message = message;
    }

    /**
     * Get message
     * 
     * @return The message
     */
    public Message getMessage() {
        return message;
    }
}
