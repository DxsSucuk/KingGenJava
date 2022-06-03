package wtf.kinggen.api;

import com.google.gson.*;
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;
import wtf.kinggen.entities.KingGenResponse;
import wtf.kinggen.exceptions.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * A Request-Utility class for the KingGen Request.
 */
public class Request {

    // Instance of GSON.

    // The formatted Request URL.
    private final String requestUrl;

    // Endpoint itself.
    private final Endpoint endpoint;

    /**
     * Constructor to send a valid Request.
     *
     * @param endpoint the Endpoint.
     * @param queries  the needed Queries.
     */
    public Request(Endpoint endpoint, Object... queries) {
        this.endpoint = endpoint;
        this.requestUrl = endpoint.compile(queries);
    }

    /**
     * Send the Request that has been build.
     *
     * @return {@link KingGenResponse} with either a valid Profile or Account.
     * @throws Exception if the URL is malformed, the API-Key is invalid or blacklisted, KingGen is out of stock, the Server responded with an Invalid Response the Request itself is invalid.
     */
    public KingGenResponse request() throws Exception {
        // Create a URL.
        URL url = new URL(requestUrl);

        // Open a Connection and set every needed Data.
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 Gecko/20100316 Firefox/3.6.2 KingGenJava/1.0");
        httpURLConnection.addRequestProperty("Accept", "application/json");

        httpURLConnection.setDoOutput(true);

        httpURLConnection.connect();

        // Parse the response to a String for later use, if needed.
        String responseContent = new BufferedReader(new InputStreamReader((100 <= httpURLConnection.getResponseCode() && httpURLConnection.getResponseCode() <= 399 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

        // Check if the Server response was valid, if so continue parsing the data.
        if (httpURLConnection.getResponseCode() == 200) {
            try {
                // Check if the Endpoint is a Profile or not, if so then try to parse it into a KingGenProfile. Same for KingGenAccount.
                switch (endpoint) {
                    case PROFILE: {
                        return new KingGenResponse(new GsonBuilder().create().fromJson(responseContent, KingGenProfile.class));
                    }
                    case GENERATE: {
                        return new KingGenResponse(new GsonBuilder().create().fromJson(responseContent, KingGenAccount.class));
                    }
                    default: {
                        throw new KingGenInvalidRequestException("Endpoint not implemented.");
                    }
                }
            } catch (Exception exception) {
                throw new KingGenInvalidOperationException("Unknown failure while trying to parse data! Exception: " + exception.getMessage());
            }
        } else if (endpoint == Endpoint.GENERATE && httpURLConnection.getResponseCode() == 204) {
            // Throw since it returned an empty response.
            throw new KingGenOutOfStockException();
        } else if (endpoint == Endpoint.GENERATE && httpURLConnection.getResponseCode() == 403) {
            // Throw since it returned an empty response.
            throw new KingGenReachedLimitException();
        } else if (httpURLConnection.getResponseCode() == 401) {
            // Throw since a Not Authorized Response has been given.
            throw new KingGenInvalidApiKeyException();
        } else {
            // Throw since the cause of this error is unknown.
            throw new KingGenInvalidResponseException(responseContent);
        }
    }

}
