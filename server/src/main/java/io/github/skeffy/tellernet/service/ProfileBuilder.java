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
