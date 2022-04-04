package wtf.kinggen.entities;

/**
 * Entity class, to store information about a successful Request.
 */
public class KingGenResponse {

    // The generated KingGen Account.
    private KingGenAccount kingGenAccount;

    // The requested KingGen Profile.
    private KingGenProfile kingGenProfile;

    /**
     * Constructor to store a KingGen Account.
     * @param kingGenAccount the generated KingGen Account.
     */
    public KingGenResponse(KingGenAccount kingGenAccount) {
        this.kingGenAccount = kingGenAccount;
    }

    /**
     * Constructor to store a KingGen Profile.
     * @param kingGenProfile the requested KingGen Profile.
     */
    public KingGenResponse(KingGenProfile kingGenProfile) {
        this.kingGenProfile = kingGenProfile;
    }

    /**
     * Retrieve the stored KingGenAccount.
     * @return the KingGenAccount.
     */
    public KingGenAccount getKingGenAccount() {
        return kingGenAccount;
    }

    /**
     * Retrieve the stored KingGenProfile.
     * @return the KingGenProfile.
     */
    public KingGenProfile getKingGenProfile() {
        return kingGenProfile;
    }
}
