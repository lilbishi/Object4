package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemDTO;
import se.kth.iv1350.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.integration.FileLogger;

/**
 * Handles all interaction with the user interface.
 */
public class View {
    private Controller contr;
    private FileLogger logger = FileLogger.getInstance();

    /**
     * Creates a new instance of View.
     *
     * @param contr The controller that handles system operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a sale with hardcoded item IDs.
     */
    public void simulateSale() {
        contr.startNewSale();

        // Register revenue observers
        contr.addRevenueObserver(new TotalRevenueView());
        contr.addRevenueObserver(new TotalRevenueFileOutput());

        registerItem("arla123", 2);
        registerItem("bar456", 1);
        registerItem("Bread123", 1);
        registerItem("fail", 1);

        contr.requestDiscount(1);

        try {
            contr.payment(100f);
        } catch (Exception e) {
            System.out.println("[Payment error] Could not complete payment: " + e.getMessage());
        }
    }

    /**
     * Tries to register an item and handles any exceptions that occur.
     *
     * @param itemID   The item identifier.
     * @param quantity The quantity of the item.
     */
    private void registerItem(String itemID, int quantity) {
        try {
            ItemDTO item = contr.registerItem(itemID, quantity);
            displayItemInfo(item, quantity);
            System.out.printf("Total price so far: %.2f SEK\n\n", contr.getCurrentTotalPrice());
        } catch (ItemNotFoundException e) {
            System.out.println("[User error] " + e.getMessage());
        } catch (DatabaseFailureException e) {
            System.out.println("[System error] Something went wrong, please try again.");
            logger.logException(e);
        } catch (Exception e) {
            System.out.println("[Unknown error] " + e.getMessage());
        }
    }

    /**
     * Displays the information of the registered item.
     *
     * @param item   The item to display.
     * @param amount The quantity of the item.
     */
    private void displayItemInfo(ItemDTO item, int amount) {
        System.out.println("Item ID: " + item.getItemID());
        System.out.println("Name: " + item.getName());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Price per item: " + item.getPrice() + " SEK");
        System.out.println("VAT: " + (item.getVAT() * 100) + "%");
        System.out.println("Quantity: " + amount);
    }
}
