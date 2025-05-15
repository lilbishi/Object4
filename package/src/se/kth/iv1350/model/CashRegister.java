package se.kth.iv1350.model;

/**
 * Represents the cash register that stores all payments.
 */
public class CashRegister {
    private float balance;

    /**
     * Creates a new instance of CashRegister with an initial balance of 0.
     */
    public CashRegister() {
        this.balance = 0;
    }

    /**
     * Adds a payment to the cash register's balance.
     *
     * @param payment The payment to add.
     */
    public void addPayment(Payment payment) {
        balance += payment.getAmountPaid();
    }

    /**
     * @return The current balance of the cash register.
     */
    public float getBalance() {
        return balance;
    }
}

