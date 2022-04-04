package wtf.kinggen;

import wtf.kinggen.api.Endpoint;
import wtf.kinggen.api.Request;
import wtf.kinggen.entities.KingGenResponse;

/**
 * Wrapper Main class, used to store the API-Key as well as do the direct data processing and requesting.
 * @author Presti
 * @version 1.0
 */
public class KingGen {

    // The API-Key.
    private final String apiKey;

    /**
     * Constructor to create an instance of the Wrapper.
     * @param apiKey the API-Key that should be used.
     */
    public KingGen(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Send a Request to the wanted Endpoint.
     * @param endpoint the {@link Endpoint}.
     * @return a {@link KingGenResponse} with the requested Account/Profile.
     * @throws Exception if the URL is malformed, the API-Key is invalid or blacklisted, KingGen is out of stock, the Server responded with an Invalid Response the Request itself is invalid.
     */
    public KingGenResponse request(Endpoint endpoint) throws Exception {
        return new Request(endpoint, apiKey).request();
    }

    /**
     * Get the API-Key used for this instance of the Wrapper.
     * @return the API-Key.
     */
    public String getApiKey() {
        return apiKey;
    }
}
