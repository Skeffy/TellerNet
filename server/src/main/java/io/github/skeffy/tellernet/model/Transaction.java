package io.github.skeffy.tellernet.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private int transactionId;
    private int accountId;
    private Timestamp time;
    private BigDecimal amount;
    private String description;

    public Transaction(int transactionId, int accountId, Timestamp time, BigDecimal amount, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.time = time;
        this.amount = amount;
        this.description = description;
    }
}
