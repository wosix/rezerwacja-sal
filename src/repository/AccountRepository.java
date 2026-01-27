package repository;

import entity.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IRepository<Account, String> {
    private static AccountRepository instance;
    private final List<Account> accounts = new ArrayList<>();

    private AccountRepository() {}

    public static AccountRepository getInstance() {
        if (instance == null) instance = new AccountRepository();
        return instance;
    }

    @Override
    public void dodajNowy(Account item) { accounts.add(item); }

    @Override
    public void usunPoId(String email) { accounts.removeIf(a -> a.getEmail().equals(email)); }

    @Override
    public List<Account> zwrocWszystkie() { return new ArrayList<>(accounts); }

    @Override
    public Account zwrocJedenPoId(String email) {
        return accounts.stream().filter(a -> a.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public void edytujPoId(String email, Account item) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getEmail().equals(email)) {
                accounts.set(i, item);
                break;
            }
        }
    }
}