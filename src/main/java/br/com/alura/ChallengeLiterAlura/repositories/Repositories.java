package br.com.alura.ChallengeLiterAlura.repositories;

import java.util.List;

public interface Repositories <T , ID> {
    Long create(T entity);

    List<T> findAll();

    int update(T entity, ID sku);

    T findById(ID sku);

    int delete(ID sku);
}
