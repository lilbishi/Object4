package se.kth.iv1350.model;

/**
 * Represents an item in the inventory.
 */
public class Item {
    private ItemDTO itemDTO;

    /**
     * Creates a new instance of Item with the provided item information.
     *
     * @param itemDTO The data transfer object containing item information.
     */
    public Item(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    /**
     * Gets the data transfer object for this item.
     *
     * @return The ItemDTO representing this item.
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }
}

