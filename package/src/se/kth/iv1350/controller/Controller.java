package se.kth.iv1350.controller;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;

/**
 * This is the application's only controller.
 * All calls to model and integration pass through here.
 */
public class Controller {
    private Printer printer;
    private Sale sale;
    private InventoryManagement inventory;
    private AccountingSystem accounting;
    private DiscountFactory discount;
    private CashRegister cashRegister;
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();

    public Controller(Printer printer, InventoryManagement inventory, DiscountFactory discount,
                      AccountingSystem accounting, CashRegister cashRegister) {
        this.printer = printer;
        this.inventory = inventory;
        this.discount = discount;
        this.accounting = accounting;
        this.cashRegister = cashRegister;
    }

    public void startNewSale() {
        sale = new Sale();
        for (RevenueObserver observer : revenueObservers) {
            sale.addRevenueObserver(observer);
        }
    }

    /**
     * Registers an item by fetching it from inventory and adding it to the current sale.
     *
     * @param itemID The ID of the item to register.
     * @param quantity The quantity of the item.
     * @return The item that was registered.
     * @throws ItemNotFoundException if the item could not be found in the inventory.
     * @throws DatabaseFailureException if there is an issue with the database when fetching the item.*/
    
    public ItemDTO registerItem(String itemID, int quantity)
            throws ItemNotFoundException, DatabaseFailureException {
        ItemDTO item = inventory.fetchItem(itemID, "employee123");
        sale.addItem(item, quantity);
        return item;
    }

    public float getCurrentTotalPrice() {
        return sale.calculateTotal();
    }

    public SaleDTO endSale() {
        return sale.getSaleInfo();
    }

    /**
     * Applies a discount to the current sale based on customer ID.
     *
     * @param customerID The ID of the customer.
     * @return The discount applied (as a fraction, e.g., 0.1 for 10%).
     */
    public float requestDiscount(int customerID) {
        SaleDTO saleInfo = sale.getSaleInfo();
        float discountAmount = discount.fetchDiscountInfo(customerID, saleInfo);
        sale.applyDiscount(discountAmount);
        return discountAmount;
    }

    /**
     * Registers payment for the sale, generates and prints the receipt,and updates all external systems.
     *
     * @param amountPaid The amount paid by the customer.
     * @return The change to give back to the customer.
     * @throws IllegalStateException if no sale has been started before calling this method.
     */
    public float payment(float amountPaid) {
        if (sale == null) {
            throw new IllegalStateException("No sale has been started.");
        }

        sale.setSaleOfTime();
        sale.regPayment(amountPaid);


        ReceiptDTO receipt = sale.generateReceipt();
        printer.printReceipt(receipt);

        SaleDTO saleInfo = sale.getSaleInfo();
        accounting.updateAccounting(saleInfo);
        inventory.updateInventory(saleInfo);
        cashRegister.addPayment(sale.getPayment());

        return sale.getPayment().calculateChange(saleInfo.getTotalPrice());
    }

    public void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }
}
