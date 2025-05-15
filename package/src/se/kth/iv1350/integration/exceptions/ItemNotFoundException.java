package se.kth.iv1350.integration.exceptions;

/**
 * Thrown when an item is not found in the inventory system.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a message describing the problem.
     *
     * @param itemID The item identifier that could not be found.
     */
    public ItemNotFoundException(String itemID) {
        super("Item with ID '" + itemID + "' was not found in inventory.");
    }
}