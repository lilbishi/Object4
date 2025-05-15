package se.kth.iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountFactoryTest {
    private DiscountFactory discountFactory;
    private SaleDTO dummySale;

    @BeforeEach
    void setUp() {
        discountFactory = new DiscountFactory();
        dummySale = new SaleDTO(100f, 6f, LocalDateTime.now(), 100f);
    }

    @Test
    void testCustomerWithDiscountReceivesTenPercent() {
        float discount = discountFactory.fetchDiscountInfo(1, dummySale);
        assertEquals(0.10f, discount, 0.001f);
    }

    @Test
    void testCustomerWithoutDiscountReceivesZero() {
        float discount = discountFactory.fetchDiscountInfo(999, dummySale);
        assertEquals(0.0f, discount, 0.001f);
    }

    @Test
    void testSetStrategyOverridesOriginalBehavior() {
        DiscountAmount noDiscountStrategy = (customerID, sale) -> 0.0f;
        discountFactory.setStrategy(noDiscountStrategy);

        float discount = discountFactory.fetchDiscountInfo(1, dummySale);
        assertEquals(0.0f, discount, 0.001f);
    }
}

