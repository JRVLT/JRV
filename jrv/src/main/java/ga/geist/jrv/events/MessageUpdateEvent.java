package ga.geist.jrv.events;

import ga.geist.jrv.types.Message;

/**
 * Message Update Event — Fired when a message updates
 */
public class MessageUpdateEvent extends Event {
    private Message oldMessage;
    private Message newMessage;

    /**
     * Construct a new Message Update Event
     * 
     * @param oldMessage Old mmessage
     * @param newMessage New message
     */
    public MessageUpdateEvent(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    /**
     * Retrieve the new message.
     * 
     * @return The new message
     */
    public Message getNewMessage() {
        return newMessage;
    }

    /**
     * Retrieve the old message.
     * 
     * @return The old message
     */
    public Message getOldMessage() {
        return oldMessage;
    }
}
