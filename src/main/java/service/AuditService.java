package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AuditService {

    private List<Transaction> auditLog;

    // Constructor: inicializa el registro vacío
    public AuditService() {
        this.auditLog = new ArrayList<>();
    }

    // Agrega una transacción al registro
    public void record(Transaction transaction) {
        auditLog.add(transaction);
    }

    // Devuelve todas las transacciones registradas
    public List<Transaction> getAuditLog() {
        return auditLog;
    }

    // Limpia el registro (útil para tests o reinicios)
    public void clearLog() {
        auditLog.clear();
    }
}
