package se.kth.iv1350.model;

public class DiscountPercent implements DiscountAmount {
    @Override
    public float getDiscount(int customerID, SaleDTO sale) {
        if (customerID == 1) {
            return 0.10f;
        }
        return 0.0f;
    }
}