package org.example.service;

import org.example.model.Account;
import org.example.model.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    Account getById(int id);

    Optional<Account> login(String email, String password);

    void create(AccountDTO account);

    void delete(int id);

    Account updateAccount(int id, Account account);

}
