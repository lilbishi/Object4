package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.CashRegister;
import se.kth.iv1350.view.View;

/*
 * Starts the entire application.
 */
public class Main {
    /**
     * The main method that starts the application.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        AccountingSystem accounting = new AccountingSystem();
        InventoryManagement inventory = new InventoryManagement();
        DiscountFactory discount = new DiscountFactory();
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister();

        Controller contr = new Controller(printer, inventory, discount, accounting, cashRegister);
        View view = new View(contr);

        view.simulateSale();
    }
}

