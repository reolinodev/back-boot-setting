package com.back.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonRepository<T> {
    Optional<T> findById(int id);

    List<T> findAll(T entity);

    int save(T entity);

    int deleteById(int id);

}
