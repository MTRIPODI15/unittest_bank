package test;

import model.Account;
import model.AccountStatus;
import org.junit.jupiter.api.Test;
import service.BankService;

import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    public void testTransferSuccessful() {
        Account from = new Account("Mati", 1000, AccountStatus.ACTIVE);
        Account to = new Account("Lucía", 500, AccountStatus.ACTIVE);
        BankService bank = new BankService();

        bank.transfer(from, to, 300);

        assertEquals(700, from.getBalance(), 0.01);
        assertEquals(800, to.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailsIfInsufficientFunds() {
        Account from = new Account("Mati", 100, AccountStatus.ACTIVE);
        Account to = new Account("Lucía", 500, AccountStatus.ACTIVE);
        BankService bank = new BankService();

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(from, to, 300));
    }

    @Test
    public void testTransferFailsIfAccountBlocked() {
        Account from = new Account("Mati", 1000, AccountStatus.BLOCKED);
        Account to = new Account("Lucía", 500, AccountStatus.ACTIVE);
        BankService bank = new BankService();

        assertThrows(IllegalStateException.class, () -> bank.transfer(from, to, 100));
    }
}