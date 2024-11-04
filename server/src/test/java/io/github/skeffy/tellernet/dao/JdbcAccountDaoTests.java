package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDaoTests extends BaseDaoTests{
    protected static final Account ACCOUNT_1 = new Account(1, 1, "checking", new BigDecimal("0.00"), new ArrayList<>());
    protected static final Account ACCOUNT_2 = new Account(8, 1, "new", new BigDecimal("0.00"), new ArrayList<>());
    protected static final Account ACCOUNT_3 = new Account(7, 3, "", new BigDecimal("1056.62"), new ArrayList<>());
    protected static final Account ACCOUNT_4 = new Account(6, 5, "checking", new BigDecimal("0.00"), new ArrayList<>());
    protected static final Customer CUSTOMER_1 = new Customer(1, "Noah", "Stevens", "555-555-5555", "123 Main St", "dontstealmymoney@gmail.com", LocalDate.of(1998, 7, 22));

    private JdbcAccountDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getAccountById_returns_valid_account() {
        Account account = dao.getAccountById(ACCOUNT_4.getAccountId());

        Assert.assertNotNull(account);
        assertAccountsMatch(ACCOUNT_4, account);
    }

    @Test
    public void getAccountsByCustomer_returns_all_accounts_of_given_customer() {
        List<Account> accounts = dao.getAccountsByCustomer(CUSTOMER_1);

        Assert.assertNotNull(accounts);
        Assert.assertEquals(2, accounts.size());
        assertAccountsMatch(ACCOUNT_1, accounts.getFirst());
    }

    @Test
    public void createAccount_creates_new_account_entry() {
        Account account = dao.createAccount(new Account(8, 1, "new", new BigDecimal("0.00"), new ArrayList<>()));

        Assert.assertNotNull(account);
        assertAccountsMatch(ACCOUNT_2, account);
    }

    @Test
    public void updateAccount_correctly_updates_nickname() {
        Account account = new Account(1, 1, "updated", new BigDecimal("0.00"), new ArrayList<>());
        Account updatedAccount = dao.updateNickname(account);

        Assert.assertNotNull(updatedAccount);
        assertAccountsMatch(account, updatedAccount);
    }

    @Test
    public void deleteAccount_does_not_delete_account_without_0_balance() {
        Assert.assertThrows(DaoException.class, () -> {
            dao.deleteAccount(ACCOUNT_3);
        });
    }

    @Test
    public void deleteAccount_deletes_account_with_0_balance() {
        int rows = dao.deleteAccount(ACCOUNT_1);

        Assert.assertEquals(1, rows);
    }

    private void assertAccountsMatch(Account expected, Account actual) {
        Assert.assertEquals("Account ids do not match", expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals("Customer ids do not match", expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals("Account nicknames do not match", expected.getNickname(), actual.getNickname());
        Assert.assertEquals("Account balances do not match", expected.getBalance(), actual.getBalance());
        Assert.assertEquals("Account transactions do not match", expected.getTransactions(), actual.getTransactions());
    }
}
