package se.kth.iv1350.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale sale;
    private ItemDTO testItem;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        testItem = new ItemDTO("milk01", "Milk", 10f, 0.06f, "Desc");
    }

    @Test
    void testAddItemUpdatesTotalPrice() {
        sale.addItem(testItem, 2);
        float expectedTotal = 20f;
        assertEquals(expectedTotal, sale.calculateTotal(), 0.01f);
    }

    @Test
    void testCalculateTotalVAT() {
        sale.addItem(testItem, 2);
        assertEquals(1.2f, sale.calculateTotalVAT(), 0.01f);
    }

    @Test
    void testApplyDiscountReducesTotal() {
        sale.addItem(testItem, 1);
        sale.applyDiscount(0.10f);
        assertEquals(9f, sale.calculateTotal(), 0.01f);
    }

    @Test
    void testGenerateReceiptContainsCorrectData() {
        sale.addItem(testItem, 1);
        sale.applyDiscount(0.1f);
        sale.setSaleOfTime();
        sale.regPayment(100f);
        ReceiptDTO receipt = sale.generateReceipt();

        assertEquals(9f, receipt.getTotalPrice(), 0.01f);
        assertEquals(0.6f, receipt.getTotalVAT(), 0.01f);
        assertEquals(100f, receipt.getAmountPaid(), 0.01f);
        assertEquals(91f, receipt.getChange(), 0.01f);
        assertEquals(0.1f, receipt.getDiscountAmount(), 0.001f);
        assertNotNull(receipt.getDate());
    }

    @Test
    void testRevenueObserverIsNotified() {
        final boolean[] wasNotified = {false};

        sale.addRevenueObserver(revenue -> wasNotified[0] = true);
        sale.addItem(testItem, 1);
        sale.setSaleOfTime();
        sale.regPayment(100f);

        assertTrue(wasNotified[0], "Observer should be notified");
    }
}

