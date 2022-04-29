package wtf.kinggen.entities;

/**
 * Entity class, to store information about a generated Account.
 */
public class KingGenAccount {

    // The Email of the generated Account.
    private final String accountEmail;

    // The Password of the generated Account.
    private final String accountPassword;

    /**
     * Constructor to store the Data.
     * @param accountEmail the Email of the Account.
     * @param accountPassword the Password of the Account.
     */
    public KingGenAccount(String accountEmail, String accountPassword) {
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    /**
     * Retrieve the stored Email.
     * @return the Email.
     */
    public String getAccountEmail() {
        return accountEmail;
    }

    /**
     * Retrieve the stored Password.
     * @return the Password.
     */
    public String getAccountPassword() {
        return accountPassword;
    }
}
