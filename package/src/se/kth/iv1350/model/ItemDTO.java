package se.kth.iv1350.model;

/**
 * Data Transfer Object for item information.
 */
public class ItemDTO {
    private String itemID;
    private String name;
    private float price;
    private float VAT;
    private String description;

    /**
     * Creates a new instance of ItemDTO.
     *
     * @param itemID The identifier for the item.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param VAT The VAT rate of the item (e.g., 0.06 for 6% VAT).
     * @param description A description of the item.
     */
    public ItemDTO(String itemID, String name, float price, float VAT, String description) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.VAT = VAT;
        this.description = description;
    }

    /**
     * @return The item's ID.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @return The item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The item's price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return The item's VAT rate.
     */
    public float getVAT() {
        return VAT;
    }

    /**
     * @return The item's description.
     */
    public String getDescription() {
        return description;
    }
}

