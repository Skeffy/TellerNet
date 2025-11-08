package io.github.skeffy.tellernet.service;

import io.github.skeffy.tellernet.dao.*;
import io.github.skeffy.tellernet.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileBuilder {

    private AccountDao accountDao;
    private CustomerDao customerDao;
    private TransactionDao transactionDao;

    public ProfileBuilder(AccountDao accountDao, CustomerDao customerDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
        this.transactionDao = transactionDao;
    }

    public Profile createProfileByCustomer(int customerId) {
        Profile profile = new Profile();
        profile.setCustomer(customerDao.getCustomerById(customerId));
        profile.setAccounts(accountDao.getAccountsByCustomer(customerId));
        profile.setAccountTransactions(getAccountTransactions(profile));
        return profile;
    }

    public Profile createProfileByAccount(int accountId) {
        Profile profile = new Profile();
        Account account = accountDao.getAccountById(accountId);
        profile.setCustomer(customerDao.getCustomerById(account.getCustomerId()));
        profile.setAccounts(accountDao.getAccountsByCustomer(profile.getCustomer().getCustomerId()));
        profile.setAccountTransactions(getAccountTransactions(profile));
        return profile;
    }

    private List<List<Transaction>> getAccountTransactions(Profile profile) {
        List<List<Transaction>> accountTransactions = new ArrayList<>();
        for (Account account : profile.getAccounts()) {
            accountTransactions.add(transactionDao.getTransactionsByAccount(account.getAccountId()));
        }
        return accountTransactions;
    }
}
