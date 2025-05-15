package se.kth.iv1350.integration;

import java.time.format.DateTimeFormatter;
import se.kth.iv1350.model.ItemDTO;
import se.kth.iv1350.model.ReceiptDTO;

import java.util.List;

/**
 * Handles the printing of receipts.
 */
public class Printer {
    /**
     * Creates a new instance of Printer.
     */
    public Printer() {}

    /**
     * Prints the receipt information to System.out.
     *
     * @param receipt The receipt to be printed.
     */
    public void printReceipt(ReceiptDTO receipt) {
        System.out.println("\n********** Welcome to our store! **********\n");

        System.out.println("*******************************************");
        System.out.println("                 Receipt\n");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateAndTime = receipt.getDate().format(format);
        System.out.println("Date & Time of the sale: " + dateAndTime);

        System.out.println("Quantity   Item                  Price Per Item");
        System.out.println("----------------------------------------------");

        List<ItemDTO> items = receipt.getItems();
        List<Integer> quantities = receipt.getQuantities();

        for (int i = 0; i < items.size(); i++) {
            ItemDTO item = items.get(i);
            int qty = quantities.get(i);

            String priceFormatted = String.format("%.2f", item.getPrice()).replace('.', ',');
            System.out.printf("%-10s %-24s %6s SEK\n", qty + "x", item.getName(), priceFormatted);
        }

        System.out.println();
        String totalFormatted = String.format("%.2f", receipt.getTotalPrice()).replace('.', ',');
        String vatFormatted = String.format("%.2f", receipt.getTotalVAT()).replace('.', ',');
        String paidFormatted = String.format("%.2f", receipt.getAmountPaid()).replace('.', ',');
        String discountFormatted = String.format("%.0f%%", receipt.getDiscountAmount() * 100);
        String changeFormatted = String.format("%.2f", receipt.getChange()).replace('.', ',');
        String revenueFormatted = String.format("%.2f", receipt.getTotalRevenue()).replace('.', ',');

        System.out.printf("Total price(incl. VAT): %s SEK\n", totalFormatted);
        System.out.printf("Total VAT: %s SEK\n", vatFormatted);
        System.out.printf("The amount paid: %s SEK\n", paidFormatted);
        System.out.printf("Discount amount: %s\n", discountFormatted);
        System.out.printf("Change: %s SEK\n", changeFormatted);
        System.out.printf("Total revenue (all sales): %s SEK\n", revenueFormatted);
        System.out.println("*******************************************\n");
    }
}
