package io.github.skeffy.tellernet.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private int accountId;
    @NotNull
    private int customerId;
    private String nickname = "";
    BigDecimal balance = BigDecimal.valueOf(0.00);
    List<Transaction> transactions = new ArrayList<>();

    public Account(int accountId, int customerId, String nickname, BigDecimal balance, List<Transaction> transactions) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.nickname = nickname;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Account() {

    }

    public int getAccountId() {
        return accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getNickname() {
        return nickname;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
