package se.kth.iv1350.model;

import java.time.LocalDateTime;



/**
 * Represents a summary of a completed sale.
 */
public class SaleDTO {
    private float totalPrice;
    private float totalVAT;
    private LocalDateTime date;
    private float amountPaid;

    /**
     * Creates a new instance of SaleDTO.
     *
     * @param totalPrice The total price after any discounts.
     * @param totalVAT The total VAT amount.
     * @param date The date and time when the sale occurred.
     * @param amountPaid The amount paid by the customer.
     */
    public SaleDTO(float totalPrice, float totalVAT, LocalDateTime date, float amountPaid) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.date = date;
        this.amountPaid = amountPaid;
    }

    /**
     * @return The total price after discounts.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return The total VAT amount.
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * @return The date and time of the sale.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return The amount paid by the customer.
     */
    public float getAmountPaid() {
        return amountPaid;
    }
}

