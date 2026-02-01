package org.example.service;

import org.example.exception.LoginException;
import org.example.exception.NotFoundException;
import org.example.exception.RegisterException;
import org.example.model.dto.AccountDTO;
import org.example.model.entity.Account;
import org.example.repository.AccountRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AccountServiceImpl implements AccountService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@].*@.+\\..+$");

    private final AccountRepositoryImpl accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika o id: " + id));
    }

    @Override
    public Account login(String email, String password) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new LoginException("Nie ma użytkownika o takim email!"));

        if (!authenticateLogin(account, password)) {
            throw new LoginException("Błędne hasło!");
        }
        return account;
    }

    private boolean authenticateLogin(Account account, String password) {
        return account.getPassword().equals(password);
    }

    @Override
    public void create(AccountDTO accountDTO) {
        if (!isValidEmail(accountDTO.getEmail())) {
            throw new RegisterException("Błędny adres email!");
        }
        if (isEmailExists(accountDTO.getEmail())) {
            throw new RegisterException("Konto o takim email już istnieje!");
        }
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

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isEmailExists(String email) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        return accountOpt.isPresent();
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
