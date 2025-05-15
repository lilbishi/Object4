package se.kth.iv1350.model;

public interface DiscountAmount {
    float getDiscount(int customerID, SaleDTO sale);
}