package wtf.kinggen.api;

import com.google.gson.*;
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;
import wtf.kinggen.entities.KingGenResponse;
import wtf.kinggen.exceptions.KingGenInvalidApiKeyException;
import wtf.kinggen.exceptions.KingGenInvalidRequestException;
import wtf.kinggen.exceptions.KingGenInvalidResponseException;
import wtf.kinggen.exceptions.KingGenOutOfStockException;

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
            JsonElement jsonElement = JsonParser.parseString(responseContent);

            // Check if it is a valid JsonObject.
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (endpoint == Endpoint.GENERATE) {
                    // Check if the Object contains the Alt specific data, if not throw exception.
                    if (jsonObject.has("email") && jsonObject.has("password") && jsonObject.has("stock")) {
                        return new KingGenResponse(new KingGenAccount(jsonObject.get("email").getAsString(), jsonObject.get("password").getAsString(), jsonObject.get("stock").getAsInt()));
                    } else {
                        throw new KingGenInvalidResponseException(responseContent);
                    }
                } else if (endpoint == Endpoint.PROFILE) {
                    // Check if the Object contains the Profile specific data, if not throw exception.
                    if (jsonObject.has("username") && jsonObject.has("generated") && jsonObject.has("generatedToday") && jsonObject.has("stock")) {
                        return new KingGenResponse(new KingGenProfile(jsonObject.get("username").getAsString(), jsonObject.get("generated").getAsInt(), jsonObject.get("generatedToday").getAsInt(), jsonObject.get("stock").getAsInt()));
                    } else {
                        throw new KingGenInvalidResponseException(responseContent);
                    }
                } else {
                    // Throw an Exception, because the given Endpoint has not been implemented yet.
                    throw new KingGenInvalidRequestException();
                }
            } else {
                // Throw an Exception since the response is not a valid JsonObject.
                throw new KingGenInvalidResponseException(responseContent);
            }
        } else if (endpoint == Endpoint.GENERATE && httpURLConnection.getResponseCode() == 204) {
            // Throw since it returned an empty response.
            throw new KingGenOutOfStockException();
        } else if (httpURLConnection.getResponseCode() == 401) {
            // Throw since a Not Authorized Response has been given.
            throw new KingGenInvalidApiKeyException();
        } else {
            // Throw since the cause of this error is unknown.
            throw new KingGenInvalidResponseException(responseContent);
        }
    }

}
