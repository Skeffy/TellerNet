package io.github.skeffy.tellernet.service;

import io.github.skeffy.tellernet.dao.AccountDao;
import io.github.skeffy.tellernet.dao.TransactionDao;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionService {

    private AccountDao accountDao;
    private TransactionDao transactionDao;


    public TransactionService(AccountDao accountDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }

    public Transaction transact(Transaction transaction) {
        Transaction newTransaction = null;
        Account account = accountDao.getAccountById(transaction.getAccountId());
        BigDecimal newBalance = account.getBalance().add(transaction.getAmount());
        account.setBalance(newBalance);
        newTransaction = transactionDao.createTransaction(transaction);
        accountDao.transact(account);
        return newTransaction;
    }
}
