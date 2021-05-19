package ga.geist.jrv;

import org.junit.Test;

import ga.geist.jrv.registries.RegistryRegistry;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

public class RevoltBridgeTest {
    private static final String API_URL = "https://api.revolt.chat";

    @Test
    public void testGetRestUrl() throws URISyntaxException {
        URI url = new URI(API_URL);
        RevoltBridge jrv = new RevoltBridge(url);

        assertEquals("getRestURL should return the correct URL", url, jrv.getRestUrl());
    }

    @Test
    public void testGetConnector() throws URISyntaxException {
        URI url = new URI(API_URL);
        RevoltBridge jrv = new RevoltBridge(url);

        assertTrue("getConnector should return a connector", jrv.getConnector() instanceof SocketConnector);
    }

    @Test
    public void testGetRegistries() throws URISyntaxException {
        URI url = new URI(API_URL);
        RevoltBridge jrv = new RevoltBridge(url);

        assertTrue("getRegistries should return a RegistryRegistry", jrv.getRegistries() instanceof RegistryRegistry);
    }
}
