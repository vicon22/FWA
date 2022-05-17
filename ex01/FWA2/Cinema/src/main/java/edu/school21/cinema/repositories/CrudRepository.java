package edu.school21.cinema.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(String email);
}

