package wtf.kinggen;

import wtf.kinggen.api.Endpoint;
import wtf.kinggen.api.Request;
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;
import wtf.kinggen.entities.KingGenResponse;
import wtf.kinggen.entities.KingGenStock;
import wtf.kinggen.exceptions.KingGenInvalidOperationException;

import java.io.InvalidObjectException;
import java.util.function.Consumer;

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
    public KingGenResponse fetch(Endpoint endpoint) throws Exception {
        return new Request(endpoint, apiKey).request();
    }

    /**
     * Send a Request to the wanted Endpoint.
     * @param endpoint the {@link Endpoint}.
     * @param success a {@link Consumer<KingGenResponse>} used as Callback if the request was successful.
     * @param failed a {@link Consumer<Throwable>} used as Callback if the request failed.
     * @throws InvalidObjectException if the given {@param success} is null.
     */
    public void fetchASync(Endpoint endpoint, Consumer<KingGenResponse> success, Consumer<Throwable> failed) throws InvalidObjectException {
        if (success == null) throw new InvalidObjectException("Callback Consumer success can not be null.");
        new Thread(() -> {
            try {
                success.accept(new Request(endpoint, apiKey).request());
            } catch (Exception exception) {
                if (failed != null) failed.accept(exception);
            }
        }).start();
    }

    /**
     * Generate a new Account.
     * @return a new Account in an Instance of  {@link KingGenAccount}.
     * @throws Exception if there was a problem with getting the Account.
     */
    public KingGenAccount fetchAccount() throws Exception {
        KingGenResponse kingGenResponse = fetch(Endpoint.GENERATE);

        if (alwaysFetch) {
            fetchProfile();
        }

        if (kingGenResponse.getKingGenAccount() != null) {
            return lastAccount = kingGenResponse.getKingGenAccount();
        }

        throw new KingGenInvalidOperationException("Response does not contain a Account!");
    }

    /**
     * Fetch a new Account ASync.
     * @param success a {@link Consumer<KingGenAccount>} used as Callback if the request was successful.
     * @param failed a {@link Consumer<Throwable>} used as Callback if the request failed.
     * @throws InvalidObjectException if the given {@param success} is null.
     */
    public void fetchAccountASync(Consumer<KingGenAccount> success, Consumer<Throwable> failed) throws InvalidObjectException {
        fetchASync(Endpoint.GENERATE, kingGenResponse -> {
            if (alwaysFetch) {
                try {
                    fetchProfileASync(null, failed);
                } catch (InvalidObjectException exception) {
                    failed.accept(exception);
                    return;
                }
            }

            if (kingGenResponse.getKingGenAccount() != null) {
                if (success != null) success.accept(lastAccount = kingGenResponse.getKingGenAccount());
                return;
            }

            if (failed != null) failed.accept(new KingGenInvalidOperationException("Response does not contain a Account!"));
        }, failed);
    }

    /**
     * Fetch the {@link KingGenProfile} for overall information on the user.
     * @return an Instance of the {@link KingGenProfile}.
     * @throws Exception if there was a problem with getting the Profile.
     */
    public KingGenProfile fetchProfile() throws Exception {
        KingGenResponse kingGenResponse = fetch(Endpoint.PROFILE);

        if (kingGenResponse.getKingGenProfile() != null) {
            return kingGenProfile = kingGenResponse.getKingGenProfile();
        }

        throw new KingGenInvalidOperationException("Response does not contain a Profile!");
    }

    /**
     * Fetch the {@link KingGenProfile} for overall information on the user ASync.
     * @param success a {@link Consumer<KingGenProfile>} used as Callback if the request was successful.
     * @param failed a {@link Consumer<Throwable>} used as Callback if the request failed.
     * @throws InvalidObjectException if the given {@param success} is null.
     */
    public void fetchProfileASync(Consumer<KingGenProfile> success, Consumer<Throwable> failed) throws InvalidObjectException {
        fetchASync(Endpoint.PROFILE, kingGenResponse -> {
            if (kingGenResponse.getKingGenProfile() != null) {
                if (success != null) success.accept(kingGenProfile = kingGenResponse.getKingGenProfile());
                return;
            }

            if (failed != null) failed.accept(new KingGenInvalidOperationException("Response does not contain a Profile!"));
        }, failed);
    }

    /**
     * Fetch the {@link KingGenStock} to find out how many accounts are available.
     * @return an Instance of the {@link KingGenStock}.
     * @throws Exception if there was a problem with getting the Stock-Information.
     */
    public KingGenStock fetchStock() throws Exception {
        KingGenResponse kingGenResponse = fetch(Endpoint.PROFILE);

        if (kingGenResponse.getKingGenProfile() != null) {
            if (alwaysFetch) {
                kingGenProfile = kingGenResponse.getKingGenProfile();
            }

            return new KingGenStock(kingGenResponse.getKingGenProfile().getStock());
        }

        throw new KingGenInvalidOperationException("Response does not contain a Profile!");
    }

    /**
     * Fetch the {@link KingGenStock} to find out how many accounts are available ASync.
     * @param success a {@link Consumer<KingGenStock>} used as Callback if the request was successful.
     * @param failed a {@link Consumer<Throwable>} used as Callback if the request failed.
     * @throws InvalidObjectException if the given {@param success} is null.
     */
    public void fetchStockASync(Consumer<KingGenStock> success, Consumer<Throwable> failed) throws InvalidObjectException {
        fetchASync(Endpoint.PROFILE, kingGenResponse -> {
            if (kingGenResponse.getKingGenProfile() != null) {
                if (alwaysFetch) {
                    kingGenProfile = kingGenResponse.getKingGenProfile();
                }

                success.accept(new KingGenStock(kingGenResponse.getKingGenProfile().getStock()));
                return;
            }

            failed.accept(new KingGenInvalidOperationException("Response does not contain a Profile!"));
        }, failed);
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
