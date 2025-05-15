package se.kth.iv1350.integration;

import se.kth.iv1350.model.DiscountAmount;
import se.kth.iv1350.model.DiscountPercent;
import se.kth.iv1350.model.SaleDTO;

public class DiscountFactory {
    private DiscountAmount strategy;

    public DiscountFactory() {
        this.strategy = new DiscountPercent();
    }

    public void setStrategy(DiscountAmount strategy) {
        this.strategy = strategy;
    }

    public float fetchDiscountInfo(int customerID, SaleDTO sale) {
        return strategy.getDiscount(customerID, sale);
    }
}
