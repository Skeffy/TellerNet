package io.github.skeffy.tellernet.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private int accountId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String description;

    public Transaction(int transactionId, int accountId, LocalDate transactionDate, BigDecimal amount, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionDate = transactionDate;
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
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
