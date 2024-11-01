package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Transaction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transaction getTransactionById(int id) {
        Transaction transaction = new Transaction();
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transaction = mapRowToTransaction(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Account account) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE account_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account.getAccountId());
            while (results.next()) {
                transactions.add(mapRowToTransaction(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transactions;
    }

    @Override
    public Transaction createTransaction(BigDecimal amount, String description, Account account, Timestamp timestamp) {
        Transaction transaction = new Transaction();
        String sql = "INSERT INTO transaction(amount, description, time, account_id) VALUES (?, ?, ?, ?) RETURNING transaction_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, amount, description, timestamp, account.getAccountId());
            transaction = getTransactionById(newId);
        } catch (NullPointerException e) {
            throw new DaoException("Error adding entry to the database", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return transaction;
    }

    private Transaction mapRowToTransaction(SqlRowSet results) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(results.getInt("transaction_id"));
        transaction.setTime(results.getTimestamp("timestamp"));
        transaction.setDescription(results.getString("description"));
        transaction.setAccountId(results.getInt("account_id"));
        transaction.setAmount(results.getBigDecimal("amount"));
        return transaction;
    }
}
