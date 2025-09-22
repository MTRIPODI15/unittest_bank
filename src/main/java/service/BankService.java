package service;

import model.Account;
import model.AccountStatus;
import model.Transaction;

public class BankService {

    // Método principal: transfiere dinero entre dos cuentas
    public void transfer(Account from, Account to, double amount) {
        // Validación de estado de ambas cuentas
        if (from.getStatus() != AccountStatus.ACTIVE || to.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Ambas cuentas deben estar activas para transferir");
        }

        // Validación de monto
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }

        // Validación de saldo suficiente
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen");
        }

        // Realiza la transferencia
        from.withdraw(amount); // ya registra la transacción
        to.deposit(amount);    // también registra la transacción

        // Agrega transacciones específicas de transferencia
        from.getTransactions().add(new Transaction("TRANSFER_OUT", amount));
        to.getTransactions().add(new Transaction("TRANSFER_IN", amount));
    }
}