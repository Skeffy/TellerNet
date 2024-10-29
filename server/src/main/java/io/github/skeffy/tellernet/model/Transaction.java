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

    public Transaction() {

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
