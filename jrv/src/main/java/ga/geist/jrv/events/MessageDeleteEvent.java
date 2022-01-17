package ga.geist.jrv.events;

import ga.geist.jrv.types.Channel;
import ga.geist.jrv.types.Message;

/**
 * Message Delete Event — Fired when a message gets deleted
 */
public class MessageDeleteEvent extends Event {

    private Message message;
    private Channel channel;

    /**
     * Construct a new Message Delete Event
     *
     * @param message Deleted message
     * @param channel Channel the message was in
     */
    public MessageDeleteEvent(Message message, Channel channel) {
        this.message = message;
        this.channel = channel;
    }

    /**
     * Retrieve the message.
     *
     * @return The message
     */
    public Message getMessage() {
        return this.message;
    }

    /**
     * Retrieve the channel the message was in.
     *
     * @return The channel of the message
     */
    public Channel getChannel() {
        return this.channel;
    }

}
