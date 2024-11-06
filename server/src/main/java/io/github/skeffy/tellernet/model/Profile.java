package io.github.skeffy.tellernet.model;

import java.util.List;

public class Profile {

    private Customer customer;
    private List<Account> accounts;
    private List<List<Transaction>> accountTransactions;

    public Profile(Customer customer, List<Account> accounts, List<List<Transaction>> accountTransactions) {
        this.customer = customer;
        this.accounts = accounts;
        this.accountTransactions = accountTransactions;
    }

    public Profile() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<List<Transaction>> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(List<List<Transaction>> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }
}
