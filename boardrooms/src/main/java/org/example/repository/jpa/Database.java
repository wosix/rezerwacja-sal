package org.example.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.entity.Account;
import org.example.model.enums.Role;
import org.example.repository.AccountRepositoryImpl;

import java.io.InputStream;
import java.util.Properties;

public class Database {

    private static final AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();

    private static EntityManagerFactory entityMF;

    public static EntityManager getEntityManager() {
        if (entityMF == null) {
            Properties props = new Properties();
            try (InputStream input = Database.class.getClassLoader()
                    .getResourceAsStream("database.properties")) {
                props.load(input);
                entityMF = Persistence.createEntityManagerFactory("boardrooms", props);
            } catch (Exception e) {
                throw new RuntimeException("Error creating entity manager factory: ", e);
            }
        }
        return entityMF.createEntityManager();
    }

    public static void createAdminAccount() {
        Account account = new Account();
        account.setFirstName("wojtek");
        account.setLastName("wosiek");
        account.setEmail("admin");
        account.setPassword("a");
        account.setRole(Role.ADMIN);
        accountRepository.save(account);
    }

}
