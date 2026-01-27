package org.example.service;

import org.example.exception.LoginException;
import org.example.exception.RegisterException;
import org.example.model.Account;
import org.example.model.dto.AccountDTO;
import org.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = AccountRepository.getInstance();
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> login(String email, String password) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        if (accountOptional.isEmpty()) {
            throw new LoginException("Nie ma użytkownika o takim email!");
        }

        Account account = accountOptional.get();

        if (!authenticateLogin(account, password)) {
            throw new LoginException("Błędne hasło!");
        }

        return accountOptional;
    }

    private boolean authenticateLogin(Account account, String password) {
        return account.getPassword().equals(password);
    }

    @Override
    public void create(AccountDTO accountDTO) {

        if (!authenticatePassword(accountDTO.getPassword(), accountDTO.getRepeatPassword())) {
            throw new RegisterException("Błędne powtórzenie hasła!");
        }

        Account account = new Account(
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getEmail(),
                accountDTO.getPassword(),
                accountDTO.getRole()
        );

        accountRepository.save(account);

    }

    private boolean authenticatePassword(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }

    @Override
    public void updateAccount(Long id, Account account) {
        accountRepository.save(account);
    }

}
