package io.github.skeffy.tellernet.dao;

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
    protected static final Customer CUSTOMER_1 = new Customer(1, "Noah", "Stevens", "555-555-5555", "123 Main St", "dontstealmymoney@gmail.com", LocalDate.of(1998, 7, 22));

    private JdbcAccountDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getAccountById_returns_valid_account() {
        Account account = dao.getAccountById(1);

        Assert.assertNotNull(account);
        Assert.assertEquals(ACCOUNT_1.getAccountId(), account.getAccountId());
        Assert.assertEquals(ACCOUNT_1.getCustomerId(), account.getCustomerId());
        Assert.assertEquals(ACCOUNT_1.getNickname(), account.getNickname());
        Assert.assertEquals(ACCOUNT_1.getBalance(), account.getBalance());
        Assert.assertEquals(ACCOUNT_1.getTransactions(), account.getTransactions());
    }

    @Test
    public void getAccountsByCustomer_returns_all_accounts_of_given_customer() {
        List<Account> accounts = dao.getAccountsByCustomer(CUSTOMER_1);

        Assert.assertNotNull(accounts);
        Assert.assertEquals(2, accounts.size());
        Assert.assertEquals(ACCOUNT_1.getAccountId(), accounts.getFirst().getAccountId());
        Assert.assertEquals(ACCOUNT_1.getCustomerId(), accounts.getFirst().getCustomerId());
        Assert.assertEquals(ACCOUNT_1.getNickname(), accounts.getFirst().getNickname());
        Assert.assertEquals(ACCOUNT_1.getBalance(), accounts.getFirst().getBalance());
        Assert.assertEquals(ACCOUNT_1.getTransactions(), accounts.getFirst().getTransactions());
    }

    @Test
    public void createAccount_creates_new_account_entry() {
        Account account = dao.createAccount(new Account(8, 1, "new", new BigDecimal("0.00"), new ArrayList<>()));

        Assert.assertNotNull(account);
        Assert.assertEquals(ACCOUNT_2.getAccountId(), account.getAccountId());
        Assert.assertEquals(ACCOUNT_2.getCustomerId(), account.getCustomerId());
        Assert.assertEquals(ACCOUNT_2.getNickname(), account.getNickname());
        Assert.assertEquals(ACCOUNT_2.getBalance(), account.getBalance());
        Assert.assertEquals(ACCOUNT_2.getTransactions(), account.getTransactions());
    }

    @Test
    public void updateAccount_correctly_updates_nickname() {
        Account account = new Account(1, 1, "updated", new BigDecimal("0.00"), new ArrayList<>());
        Account updatedAccount = dao.updateNickname(account);

        Assert.assertNotNull(updatedAccount);
        Assert.assertEquals(ACCOUNT_1.getAccountId(), updatedAccount.getAccountId());
        Assert.assertEquals(ACCOUNT_1.getCustomerId(), updatedAccount.getCustomerId());
        Assert.assertEquals(account.getNickname(), updatedAccount.getNickname());
        Assert.assertEquals(ACCOUNT_1.getBalance(), updatedAccount.getBalance());
        Assert.assertEquals(ACCOUNT_1.getTransactions(), updatedAccount.getTransactions());
    }
}
