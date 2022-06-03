package wtf.kinggen.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Entity class, to store information about a requested Profile.
 */
public class KingGenProfile {

    // The Username of the Profile.
    @SerializedName("username")
    private final String username;

    // The overall Generated Alts.
    @SerializedName("generated")
    private final int generated;

    // The amount of Alts generated today.
    @SerializedName("generatedToday")
    private final int generatedToday;

    // The amount of accounts left in the Gen.
    @SerializedName("stock")
    private final int stock;

    /**
     * Constructor to store Data.
     * @param username the Username of the User.
     * @param generated the overall generated Accounts of the User.
     * @param generatedToday the amount of generations today.
     * @param stock the current KingGen Stock.
     */
    public KingGenProfile(String username, int generated, int generatedToday, int stock) {
        this.username = username;
        this.generated = generated;
        this.generatedToday = generatedToday;
        this.stock = stock;
    }

    /**
     * Retrieve the stored Username.
     * @return the Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieve the stored Generated value.
     * @return the Generated value.
     */
    public int getGenerated() {
        return generated;
    }

    /**
     * Retrieve the stored generated Today value.
     * @return the generated Today value.
     */
    public int getGeneratedToday() {
        return generatedToday;
    }

    /**
     * Retrieve the stored Stock value.
     * @return the Stock value.
     */
    public int getStock() {
        return stock;
    }
}
