package ga.geist.jrv.types;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Defines an attachment
 */
public class Attachment {
    AttachmentMetadata metadata;
    String filename;
    String mimeType;
    long size;
    String id;
    String tag;

    /**
     * Get metadata
     * 
     * @return The metadata
     */
    public AttachmentMetadata getMetadata() {
        return metadata;
    }

    /**
     * Get the filename
     * 
     * @return The filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get the mime type
     * 
     * @return The mime type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Get the file size
     * 
     * @return The file size
     */
    public long getSize() {
        return size;
    }

    /**
     * Get the ID
     * 
     * @return The ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the tag
     * 
     * @return The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Construct an Attachment
     * 
     * @param metadata Metadata
     * @param filename File name
     * @param mimeType MIME type
     * @param size     Size in bytes
     * @param id       ID of the attachment
     * @param tag      Autumn tag
     */
    public Attachment(AttachmentMetadata metadata, String filename, String mimeType, long size, String id, String tag) {
        this.metadata = metadata;
        this.filename = filename;
        this.mimeType = mimeType;
        this.size = size;
        this.id = id;
        this.tag = tag;
    }

    /**
     * Construct an Attachment class from JSON
     * 
     * @param object A JSONObject
     * @return The constructed Attachment class
     */
    public static Attachment fromJSON(JSONObject object) {
        if (object == null)
            return null;

        JSONObject metaObject = object.getJSONObject("metadata");

        AttachmentMetadata metadata = new AttachmentMetadata(metaObject.getString("type"), metaObject.optInt("width"),
                metaObject.optInt("height"));

        return new Attachment(metadata, object.getString("filename"), object.getString("content_type"),
                object.getLong("size"), object.getString("_id"), object.getString("tag"));
    }

    /**
     * Runs Attachment.fromJSON on a JSONArray
     * 
     * @param array JSONArray of objects
     * @return Attachment object array
     */
    public static Attachment[] fromJSONArray(JSONArray array) {
        if (array == null)
            return new Attachment[] {};

        List<Attachment> attachments = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Attachment attachment = Attachment.fromJSON(array.optJSONObject(i));
            if (attachment != null)
                attachments.add(attachment);
        }

        return attachments.toArray(new Attachment[0]);
    }
}
