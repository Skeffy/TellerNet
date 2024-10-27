package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Account;

import java.util.List;

public interface AccountDao {

    Account getAccountById(int accountId);
    List<Account> getAccountsByName(String firstName, String lastName);
    List<Account> getAccountsByPhone(String phone);
    List<Account> getAccountsByEmail(String email);
    Account createAccount(Account account);
    Account updateNickname(String nickname);

}
