package rndm_access.assorteddiscoveries.config;

import java.util.Map;

public class ModClientConfig {
    private static volatile Map<String, Boolean> boolServerConfigEntries = null;

    /**
     * This config is available on the client after a player joins a world and on the server
     * after the world is started!
     *
     * @return A read-only copy of all config entries from the server config that can be used for client based
     * applications such as for creative tabs!
     */
    public static synchronized Map<String, Boolean> getBoolEntries() {
        return boolServerConfigEntries;
    }

    public static synchronized void updateBoolEntries(Map<String, Boolean> entries) {
        boolServerConfigEntries = entries;
    }
}
