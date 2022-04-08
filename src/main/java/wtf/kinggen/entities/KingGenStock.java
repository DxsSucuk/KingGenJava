package wtf.kinggen.entities;

/**
 * Entity class, to store information about the current Stock.
 */
public class KingGenStock {

    // The amount of accounts left in the Gen.
    private final int stock;

    /**
     * Constructor to store the Data.
     * @param stock the left Stock.
     */
    public KingGenStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retrieve the stored Stock value.
     * @return the Stock value.
     */
    public int getStock() {
        return stock;
    }
}
