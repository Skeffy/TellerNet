package io.github.skeffy.tellernet.service;

import io.github.skeffy.tellernet.dao.AccountDao;
import io.github.skeffy.tellernet.dao.CustomerDao;
import io.github.skeffy.tellernet.dao.TransactionDao;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.model.Profile;
import io.github.skeffy.tellernet.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProfileBuilder {

    private AccountDao accountDao;
    private CustomerDao customerDao;
    private TransactionDao transactionDao;

    public ProfileBuilder(AccountDao accountDao, CustomerDao customerDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
        this.transactionDao = transactionDao;
    }

    public Profile createProfile(Customer customer) {
        Profile profile = new Profile();
        profile.setCustomer(customer);
        profile.setAccounts(accountDao.getAccountsByCustomer(customer));
        profile.setAccountTransactions(getAccountTransactions(profile));
        return profile;
    }

    public Profile createProfile(Account account) {
        Profile profile = new Profile();
        profile.setCustomer(customerDao.getCustomerById(account.getCustomerId()));
        profile.setAccounts(accountDao.getAccountsByCustomer(profile.getCustomer()));
        profile.setAccountTransactions(getAccountTransactions(profile));
        return profile;
    }

    private List<List<Transaction>> getAccountTransactions(Profile profile) {
        List<List<Transaction>> accountTransactions = new ArrayList<>();
        for (Account account : profile.getAccounts()) {
            accountTransactions.add(transactionDao.getTransactionsByAccount(account));
        }
        return accountTransactions;
    }
}
