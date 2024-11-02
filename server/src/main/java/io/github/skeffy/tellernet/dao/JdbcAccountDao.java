package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Customer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountById(int accountId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE account_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            if(results.next()) {
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    public List<Account> getAccountsByCustomer(Customer customer) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE customer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customer.getCustomerId());
            while (results.next()) {
                accounts.add(mapRowToAccount(results));
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }

    @Override
    public Account createAccount(Account account) {
        Account newAccount;
        String sql = "INSERT INTO account(customer_id, nickname) VALUES (?, ?) returning account_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, account.getCustomerId(), account.getNickname());
            newAccount = getAccountById(newId);
        } catch (NullPointerException e) {
            throw new DaoException("Error adding entry to the database", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newAccount;
    }

    @Override
    public Account updateNickname(Account account, String nickname) {
        Account updatedAccount;
        String sql = "UPDATE account SET nickname = ? WHERE account_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, nickname, account.getAccountId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected. Expected at least one");
            }
            updatedAccount = getAccountById(account.getAccountId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedAccount;
    }

    @Override
    public int deleteAccount(Account account) {
        int rowsAffected;
        String sql = "DELETE * FROM transactions WHERE account_id = ?;" +
                "DELETE FROM account WHERE account_id = ?";
        if (account.getBalance().equals(BigDecimal.valueOf(0.00))) {
            try {
                rowsAffected = jdbcTemplate.update(sql, account.getAccountId(), account.getAccountId());
                if (rowsAffected == 0) {
                    throw new DaoException("Zero rows affected. Expected at least one");
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        } else {
            throw new DaoException("Account balance must be zero to close account");
        }
        return rowsAffected;
    }

    private Account mapRowToAccount(SqlRowSet results) {
        Account account = new Account();
        account.setAccountId(results.getInt("account_id"));
        account.setCustomerId(results.getInt("customer_id"));
        account.setNickname(results.getString("nickname"));
        account.setBalance(results.getBigDecimal("balance"));
        return account;
    }
}
