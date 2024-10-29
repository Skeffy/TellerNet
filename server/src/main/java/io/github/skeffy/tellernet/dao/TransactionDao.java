package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionDao {
    List<Transaction> getTransactionsByAccount(Account account);
    Transaction createTransaction(BigDecimal amount, String description, Account account, Timestamp timestamp);
    Transaction getTransactionById(int id);
}
