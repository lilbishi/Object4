package se.kth.iv1350.integration.exceptions;

/**
 * Thrown to simulate a database connection failure.
 */
public class DatabaseFailureException extends RuntimeException {
    /**
     * Creates a new instance with a message.
     *
     * @param itemID The item ID that triggered the failure.
     */
    public DatabaseFailureException(String itemID) {
        super("Database access failure for item ID: " + itemID);
    }
}
