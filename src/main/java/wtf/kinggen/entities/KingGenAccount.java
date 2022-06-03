package wtf.kinggen.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Entity class, to store information about a generated Account.
 */
public class KingGenAccount {

    // The Email of the generated Account.
    @SerializedName("email")
    private final String email;

    // The Password of the generated Account.
    @SerializedName("password")
    private final String password;

    /**
     * Constructor to store the Data.
     * @param accountEmail the Email of the Account.
     * @param accountPassword the Password of the Account.
     */
    public KingGenAccount(String accountEmail, String accountPassword) {
        this.email = accountEmail;
        this.password = accountPassword;
    }

    /**
     * Retrieve the stored Email.
     * @return the Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieve the stored Password.
     * @return the Password.
     */
    public String getPassword() {
        return password;
    }
}
