package test;

import model.Transaction;
import org.junit.jupiter.api.Test;
import service.AuditService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuditServiceTest {

    @Test
    public void testRecordTransaction() {
        AuditService audit = new AuditService();
        Transaction tx = new Transaction("TRANSFER", 500.0);

        audit.record(tx);
        List<Transaction> log = audit.getAuditLog();

        assertEquals(1, log.size());
        assertEquals("TRANSFER", log.get(0).getType());
        assertEquals(500.0, log.get(0).getAmount(), 0.01);
    }

    @Test
    public void testClearLog() {
        AuditService audit = new AuditService();
        audit.record(new Transaction("DEPOSIT", 100.0));
        audit.clearLog();

        assertTrue(audit.getAuditLog().isEmpty());
    }
}