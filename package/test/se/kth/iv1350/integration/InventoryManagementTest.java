package se.kth.iv1350.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.exceptions.DatabaseFailureException;
import se.kth.iv1350.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.model.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagementTest {
    private final InventoryManagement inventory = new InventoryManagement();

    @Test
    void testFetchItemReturnsCorrectItem() throws ItemNotFoundException {
        ItemDTO item = inventory.fetchItem("arla123", "emp001");
        assertEquals("arla123", item.getItemID());
        assertEquals("Arla Strawberry Milk", item.getName());
        assertEquals(29.90f, item.getPrice(), 0.01f);
    }

    @Test
    void testFetchItemThrowsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> {
            inventory.fetchItem("nonexistent", "emp001");
        });
    }

    @Test
    void testFetchItemThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            inventory.fetchItem("fail", "emp001");
        });
    }
}

