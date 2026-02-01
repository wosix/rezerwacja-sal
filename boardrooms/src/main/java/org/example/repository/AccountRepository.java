package org.example.repository;

import org.example.model.entity.Account;
import org.example.model.enums.Role;
import org.example.repository.jpa.IRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends IRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    List<Account> findByRole(Role role);
    
}
