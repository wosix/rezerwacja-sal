package org.example.repository;

import org.example.model.Account;
import org.example.model.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepository {

    private static AccountRepository instance;

    private final Map<Long, Account> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private AccountRepository() {
        save(new Account("Jan", "Kowal", "jan@example.com", "123", Role.ADMIN));
        save(new Account("woj", "Wojtek", "wojt@gmail.com", "123", Role.USER));
        save(new Account("adam", "Wosiek", "a", "a", Role.ADMIN));
    }

    public static synchronized AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(idGenerator.getAndIncrement());
        }
        database.put(account.getId(), account);
        return account;
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Account> findAll() {
        return new ArrayList<>(database.values());
    }

    public Optional<Account> findByEmail(String email) {
        return database.values().stream()
                .filter(account -> email.equals(account.getEmail()))
                .findFirst();
    }

    public List<Account> findByRole(Role role) {
        return database.values().stream()
                .filter(account -> role.equals(account.getRole()))
                .toList();
    }

    public void deleteById(Long id) {
        database.remove(id);
    }

    public boolean existsById(Long id) {
        return database.containsKey(id);
    }

    public long count() {
        return database.size();
    }

}
