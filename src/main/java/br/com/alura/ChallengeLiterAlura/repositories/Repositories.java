package br.com.alura.ChallengeLiterAlura.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repositories <T , ID> {
    void create(T entity) throws SQLException;

    List<T> findAll();

    int update(T entity, ID sku);

    T findById(ID sku);

    int delete(ID sku);
}
