package ga.geist.jrv.types;

/**
 * Short message used for previews in channel lists.
 */
public class ShortMessage {
    private String id;
    private String author;
    private String shortContents;

    /**
     * Get the ID of the channel
     * 
     * @return The ID of the channel
     */
    public String getId() {
        return id;
    }

    /**
     * Get the author of the last message
     * 
     * @return The author of the last message
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the truncated contents of the last message
     * 
     * @return the truncated contents of the last message
     */
    public String getShortContents() {
        return shortContents;
    }

    /**
     * Construct a ShortMessage instance
     * 
     * @param id            ID of the message
     * @param author        Author ID of the message
     * @param shortContents Server-truncated contents
     */
    public ShortMessage(String id, String author, String shortContents) {
        this.id = id;
        this.author = author;
        this.shortContents = shortContents;
    }
}
