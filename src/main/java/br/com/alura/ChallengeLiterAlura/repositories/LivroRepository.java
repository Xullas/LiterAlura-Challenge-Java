package br.com.alura.ChallengeLiterAlura.repositories;

import br.com.alura.ChallengeLiterAlura.domain.Livro;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.util.List;

public class LivroRepository implements Repositories<Livro, Long> {

    private final JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long create(Livro entity) {
        String sql = "INSERT INTO livro (titulo, resumo) VALUES (?, ?)";
        return 0L;
    }

    @Override
    public List<Livro> findAll() {
        return List.of();
    }

    @Override
    public int update(Livro entity, Long sku) {
        return 0;
    }

    @Override
    public Livro findById(Long sku) {
        return null;
    }

    @Override
    public int delete(Long sku) {
        return 0;
    }
}
