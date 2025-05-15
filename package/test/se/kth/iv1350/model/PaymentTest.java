package se.kth.iv1350.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testGetAmountPaidReturnsCorrectValue() {
        Payment payment = new Payment(200f);
        assertEquals(200f, payment.getAmountPaid(), 0.001f);
    }

    @Test
    void testCalculateChangeReturnsCorrectChange() {
        Payment payment = new Payment(100f);
        float totalPrice = 73.45f;
        float expectedChange = 100f - totalPrice;

        assertEquals(expectedChange, payment.calculateChange(totalPrice), 0.001f);
    }

    @Test
    void testCalculateChangeReturnsZeroWhenExactAmountPaid() {
        Payment payment = new Payment(50f);
        assertEquals(0f, payment.calculateChange(50f), 0.001f);
    }

    @Test
    void testCalculateChangeReturnsNegativeWhenUnderpaid() {
        Payment payment = new Payment(30f);
        assertEquals(-20f, payment.calculateChange(50f), 0.001f);
    }
}

