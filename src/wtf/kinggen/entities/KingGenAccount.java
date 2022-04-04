package wtf.kinggen.entities;

/**
 * Entity class, to store information about a generated Account.
 */
public class KingGenAccount {

    // Account specific Data.
    private final String accountEmail, accountPassword;

    // Current Stock.
    private final int stock;

    /**
     * Constructor to store the Data.
     * @param accountEmail the Email of the Account.
     * @param accountPassword the Password of the Account.
     * @param stock the left Stock.
     */
    public KingGenAccount(String accountEmail, String accountPassword, int stock) {
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
        this.stock = stock;
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

    /**
     * Retrieve the stored Stock value.
     * @return the Stock value.
     */
    public int getStock() {
        return stock;
    }
}
