package wtf.kinggen;

import wtf.kinggen.api.Endpoint;
import wtf.kinggen.api.Request;
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;
import wtf.kinggen.entities.KingGenResponse;
import wtf.kinggen.exceptions.KingGenInvalidOperationException;
import wtf.kinggen.exceptions.KingGenInvalidResponseException;

/**
 * Wrapper Main class, used to store the API-Key as well as do the direct data processing and requesting.
 * @author Presti
 * @version 1.0
 */
public class KingGen {

    // The API-Key.
    private final String apiKey;

    // The KingGen Profile of the User.
    private KingGenProfile kingGenProfile;

    // The last generated Account.
    private KingGenAccount lastAccount;

    // Used to indicate if the Wrapper should always fetch the KingGenProfile
    private boolean alwaysFetch = false;

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
     * Generate a new Account.
     * @return a new Account in an Instance of  {@link KingGenAccount}.
     * @throws Exception if there was a problem with getting the Account.
     */
    public KingGenAccount generateAccount() throws Exception {
        KingGenResponse kingGenResponse = request(Endpoint.GENERATE);

        if (alwaysFetch) {
            fetchProfile();
        }

        if (kingGenResponse.getKingGenAccount() != null) {
            return lastAccount = kingGenResponse.getKingGenAccount();
        }

        throw new KingGenInvalidOperationException("Response does not contain a Account!");
    }

    /**
     * Fetch the {@link KingGenProfile} for overall information on the user.
     * @return an Instance of the {@link KingGenProfile}.
     * @throws Exception if there was a problem with getting the Profile.
     */
    public KingGenProfile fetchProfile() throws Exception {
        KingGenResponse kingGenResponse = request(Endpoint.PROFILE);

        if (kingGenResponse.getKingGenProfile() != null) {
            return kingGenProfile = kingGenResponse.getKingGenProfile();
        }

        throw new KingGenInvalidOperationException("Response does not contain a Profile!");
    }

    /**
     * Change if the Wrapper should always fetch the Profile or not.
     * @param alwaysFetch true, if so | false, if not.
     */
    public void setAlwaysFetch(boolean alwaysFetch) {
        this.alwaysFetch = alwaysFetch;
    }

    /**
     * Check if the Wrapper will always fetch the Profile after a successful request.
     * @return if the Wrapper always fetches the Profile.
     */
    public boolean isAlwaysFetch() {
        return alwaysFetch;
    }

    /**
     * Retrieve the last fetched Instance of the KingGen-Profile.
     * @return the {@link KingGenProfile}.
     */
    public KingGenProfile getKingGenProfile() {
        return kingGenProfile;
    }

    /**
     * Retrieve the last fetched Instance of the KingGen-Account.
     * @return the {@link KingGenAccount}.
     */
    public KingGenAccount getLastAccount() {
        return lastAccount;
    }

    /**
     * Get the API-Key used for this instance of the Wrapper.
     * @return the API-Key.
     */
    public String getApiKey() {
        return apiKey;
    }
}
