package org.example.service;

import org.example.model.dto.AccountDTO;
import org.example.model.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getById(Long id);

    Account login(String email, String password);

    void create(AccountDTO account);

    void delete(Long id);

    void updateAccount(Long id, Account account);

}
