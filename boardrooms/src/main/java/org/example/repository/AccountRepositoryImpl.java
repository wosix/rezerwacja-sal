package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.entity.Account;
import org.example.model.enums.Role;
import org.example.repository.jpa.Database;
import org.example.repository.jpa.GenericRepository;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl extends GenericRepository<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl() {
        super(Account.class);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        try (EntityManager em = Database.getEntityManager()) {
            List<Account> accounts = em.createQuery(
                            "SELECT a FROM Account a WHERE a.email = :email", Account.class)
                    .setParameter("email", email)
                    .getResultList();
            return accounts.isEmpty() ? Optional.empty() : Optional.of(accounts.getFirst());
        }
    }

    @Override
    public List<Account> findByRole(Role role) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT a FROM Account a WHERE a.role = :role", Account.class)
                    .setParameter("role", role)
                    .getResultList();
        }
    }

}
