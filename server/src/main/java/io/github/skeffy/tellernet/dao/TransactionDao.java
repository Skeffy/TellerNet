package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getTransactionsByAccount(int accountId);
    Transaction getTransactionById(int id);
    Transaction createTransaction(Transaction transaction);
}
