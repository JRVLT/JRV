package ga.geist.jrv.utils;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

/**
 * Internal utility classes to work with REST APIs without authentication
 */
public class RestUtils {
    private RestUtils() {
    }

    /**
     * HTTP POST to a URL + a JSONObject body
     * 
     * @param uri    The URI to POST to
     * @param object A JSON object to serve as the body
     * @return A string containing the response body. Attempts to return "{}" in
     *         case of an error.
     */
    public static String postJson(URI uri, JSONObject object) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {            
            HttpPost post = new HttpPost(uri);
            post.setEntity(new StringEntity(object.toString()));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            } catch (ParseException e) {
                return "{}";
            }
        } catch (IOException e) {
            return "{}";
        }
    }

    /**
     * HTTP GET a URL
     * 
     * @param uri The URI to GET
     * @return A string containing the response body. Attempts to return "{}" in
     *         case of an error.
     */
    public static String getJson(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            } catch (ParseException e) {
                return "{}";
            }
        } catch (IOException e) {
            return "{}";
        }
    }
}
