package se.kth.iv1350.controller;

import org.junit.jupiter.api.*;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.integration.exceptions.*;
import se.kth.iv1350.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        Printer fakePrinter = new Printer() {
            @Override
            public void printReceipt(ReceiptDTO receipt) {
                // Do nothing – avoid printing
            }
        };

        InventoryManagement fakeInventory = new InventoryManagement() {
            @Override
            public ItemDTO fetchItem(String itemID, String employeeID) throws ItemNotFoundException {
                if (itemID.equals("abc123")) {
                    return new ItemDTO("abc123", "Milk", 10f, 0.06f, "Desc");
                } else if (itemID.equals("fail")) {
                    throw new DatabaseFailureException(itemID);
                } else {
                    throw new ItemNotFoundException(itemID);
                }
            }

            @Override
            public void updateInventory(SaleDTO saleDTO) {
                // No operation
            }
        };

        AccountingSystem fakeAccounting = new AccountingSystem();
        DiscountFactory fakeDiscount = new DiscountFactory() {
            @Override
            public float fetchDiscountInfo(int customerID, SaleDTO saleInfo) {
                return customerID == 1 ? 0.10f : 0.0f;
            }
        };
        CashRegister fakeCashRegister = new CashRegister();

        controller = new Controller(fakePrinter, fakeInventory, fakeDiscount, fakeAccounting, fakeCashRegister);
        controller.startNewSale();
    }

    @Test
    void testRegisterItemSuccess() throws Exception {
        ItemDTO item = controller.registerItem("abc123", 2);
        assertEquals("abc123", item.getItemID());
    }

    @Test
    void testRegisterItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> {
            controller.registerItem("unknown", 1);
        });
    }

    @Test
    void testRegisterDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.registerItem("fail", 1);
        });
    }

    @Test
    void testPaymentReturnsCorrectChangeWithoutDiscount() throws Exception {
        controller.registerItem("abc123", 1);
        float change = controller.payment(50f);
        assertEquals(40f, change, 0.01f);
    }

    @Test
    void testRequestDiscountAppliesDiscountCorrectly() throws Exception {
        controller.registerItem("abc123", 1);
        controller.requestDiscount(1);

        float change = controller.payment(50f);
        assertEquals(41f, change, 0.01f);
    }
}
