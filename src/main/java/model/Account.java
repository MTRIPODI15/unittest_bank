package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String owner;
    private double balance;
    private AccountStatus status;
    private List<Transaction> transactions;

    // Constructor: crea una cuenta con titular, saldo inicial y estado
    public Account(String owner, double balance, AccountStatus status) {
        this.owner = owner;
        this.balance = balance;
        this.status = status;
        this.transactions = new ArrayList<>();
    }

    // Deposita dinero en la cuenta si está activa
    public void deposit(double amount) {
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
    }

    // Retira dinero si hay saldo suficiente y la cuenta está activa
    public void withdraw(double amount) {
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
    }

    // Getters
    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Cambia el estado de la cuenta (por ejemplo, bloquearla)
    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}