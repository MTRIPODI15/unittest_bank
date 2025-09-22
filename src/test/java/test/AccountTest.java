package test;

import org.junit.jupiter.api.Test;

import model.Account;
import model.AccountStatus;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDepositValidAmount() {
        Account acc = new Account("Mati", 1000, AccountStatus.ACTIVE);
        acc.deposit(500);
        assertEquals(1500, acc.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawValidAmount() {
        Account acc = new Account("Mati", 1000, AccountStatus.ACTIVE);
        acc.withdraw(300);
        assertEquals(700, acc.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawMoreThanBalanceThrowsException() {
        Account acc = new Account("Mati", 1000, AccountStatus.ACTIVE);
        assertThrows(IllegalArgumentException.class, () -> acc.withdraw(1500));
    }

    @Test
    public void testDepositBlockedAccountThrowsException() {
        Account acc = new Account("Mati", 1000, AccountStatus.BLOCKED);
        assertThrows(IllegalStateException.class, () -> acc.deposit(100));
    }
}