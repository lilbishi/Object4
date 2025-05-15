package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents an ongoing sale.
 */
public class Sale {
    private Map<ItemDTO, Integer> itemMap;
    private Payment payment;
    private float totalPrice;
    private LocalDateTime saleTime;
    private float discount;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();

    private static float totalRevenueGlobal = 0;

    public Sale() {
        this.itemMap = new LinkedHashMap<>();
        this.totalPrice = 0;
        this.discount = 0;
    }

    public static float getTotalRevenueGlobal() {
        return totalRevenueGlobal;
    }

    public void addItem(ItemDTO item, int amount) {
        itemMap.put(item, itemMap.getOrDefault(item, 0) + amount);
        totalPrice += item.getPrice() * amount;
    }

    public float calculateTotal() {
        return totalPrice * (1 - discount);
    }

    public float calculateTotalVAT() {
        float vat = 0;
        for (Map.Entry<ItemDTO, Integer> entry : itemMap.entrySet()) {
            ItemDTO item = entry.getKey();
            int quantity = entry.getValue();
            vat += item.getPrice() * item.getVAT() * quantity;
        }
        return vat;
    }

    public void applyDiscount(float discount) {
        this.discount = discount;
    }

    public void setSaleOfTime() {
        this.saleTime = LocalDateTime.now();
    }

    public void regPayment(float amountPaid) {
        this.payment = new Payment(amountPaid);
        totalRevenueGlobal += calculateTotal();
        notifyObservers();
    }

    /**
     * Returns information about the sale.
     *
     * @return A SaleDTO containing sale details. Will return amountPaid = 0 if no payment has been made yet.
     */
    public SaleDTO getSaleInfo() {
        float amount = (payment != null) ? payment.getAmountPaid() : 0f;
        return new SaleDTO(calculateTotal(), calculateTotalVAT(), saleTime, amount);
    }

    public Payment getPayment() {
        return payment;
    }

    public ReceiptDTO generateReceipt() {
        if (payment == null) {
            throw new IllegalStateException("Cannot generate receipt without payment.");
        }

        float totalVAT = calculateTotalVAT();
        float change = payment.calculateChange(calculateTotal());

        List<ItemDTO> items = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Map.Entry<ItemDTO, Integer> entry : itemMap.entrySet()) {
            items.add(entry.getKey());
            quantities.add(entry.getValue());
        }

        return new ReceiptDTO(
                calculateTotal(),
                totalVAT,
                payment.getAmountPaid(),
                change,
                saleTime,
                items,
                quantities,
                discount,
                totalRevenueGlobal
        );
    }

    public void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }

    private void notifyObservers() {
        float totalRevenue = calculateTotal();
        for (RevenueObserver observer : revenueObservers) {
            observer.updateRevenue(totalRevenue);
        }
    }
}
