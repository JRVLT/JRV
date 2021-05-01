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
 * Internal utility classes to work with REST APIs that require authentication
 */
public class AuthenticatedRestUtils {
    private AuthenticatedRestUtils() {
    }

    /**
     * HTTP POST to a URL with REVOLT authentification + a JSONObject body
     * 
     * @param uri          The URI to POST to
     * @param object       A JSON object to serve as the body
     * @param userId       User ID
     * @param sessionToken Session Token
     * @return A string containing the response body. Attempts to return "{}" in
     *         case of an error.
     */
    public static String postJson(URI uri, JSONObject object, String userId, String sessionToken) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(uri);
            post.setHeader("x-user-id", userId);
            post.setHeader("x-session-token", sessionToken);
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
     * HTTP GET a URL with REVOLT authentification
     * 
     * @param uri          The URI to GET
     * @param userId       User ID
     * @param sessionToken Session Token
     * @return A string containing the response body. Attempts to return "{}" in
     *         case of an error.
     */
    public static String getJson(URI uri, String userId, String sessionToken) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(uri);
            get.setHeader("x-user-id", userId);
            get.setHeader("x-session-token", sessionToken);

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
