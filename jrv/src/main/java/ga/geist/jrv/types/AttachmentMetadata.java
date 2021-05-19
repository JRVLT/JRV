package ga.geist.jrv.types;

/**
 * Attachment metadata
 */
public class AttachmentMetadata {
    private int width;
    private int height;
    private String type;

    /**
     * Retrieve the width
     * 
     * @return The width (or null if not an image or video)
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieve the height
     * 
     * @return The height (or null if not an image or video)
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieve the type
     * 
     * @return The type, usually "Image", "Video", "Audio", "Text" or "File"
     */
    public String getType() {
        return type;
    }

    /**
     * Contruct attachment metadata
     * 
     * @param type   Attachment type
     * @param width  Attachment width
     * @param height Attachment height
     */
    public AttachmentMetadata(String type, int width, int height) {

    }
}
