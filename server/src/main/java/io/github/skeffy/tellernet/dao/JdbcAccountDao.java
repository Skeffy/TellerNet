package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

    @Override
    public List<Account> getAccountsByName(String firstName, String lastName) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account a JOIN customer c ON (c.customer_id = a.customer_id) WHERE c.first_name ILIKE ? AND c.last_name ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstName, lastName);
            while(results.next()) {
                accounts.add(mapRowToAccount(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountsByPhone(String phone) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account a JOIN customer c ON (c.customer_id = a.customer_id) WHERE phone ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, phone);
            while(results.next()) {
                accounts.add(mapRowToAccount(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountsByEmail(String email) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account a JOIN customer c ON (c.customer_id = a.customer_id) WHERE c.email ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, email);
            while(results.next()) {
                accounts.add(mapRowToAccount(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }

    @Override
    public Account createAccount(Account account) {
        Account newAccount = new Account();
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
        Account updatedAccount = null;
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

    private Account mapRowToAccount(SqlRowSet results) {
        Account account = new Account();
        account.setAccountId(results.getInt("account_id"));
        account.setCustomerId(results.getInt("customer_id"));
        account.setNickname(results.getString("nickname"));
        account.setBalance(results.getBigDecimal("balance"));
        return account;
    }
}
