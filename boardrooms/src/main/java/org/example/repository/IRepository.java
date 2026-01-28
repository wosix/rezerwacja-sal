package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, ID> {

    void save(T item);

    void delete(ID id);

    List<T> findAll();

    Optional<T> findById(ID id);

    long count();

}