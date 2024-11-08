package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Account;
import io.github.skeffy.tellernet.model.Customer;

import java.util.List;

public interface AccountDao {

    Account getAccountById(int accountId);
    List<Account> getAccountsByCustomer(Customer customer);
    Account transact(Account account);
    Account createAccount(Account account);
    Account updateNickname(Account account);
    int deleteAccount(Account account);
}
