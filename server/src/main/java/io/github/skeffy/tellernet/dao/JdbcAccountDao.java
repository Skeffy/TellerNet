package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
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
        String sql = "SELECT * FROM account WHERE first_name ILIKE ? AND last_name ILIKE ?";
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
        return null;
    }

    @Override
    public List<Account> getAccountsByEmail(String email) {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public Account updateNickname(String nickname) {
        return null;
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
