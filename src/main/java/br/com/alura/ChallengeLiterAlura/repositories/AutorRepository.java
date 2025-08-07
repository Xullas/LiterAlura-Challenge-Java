package br.com.alura.ChallengeLiterAlura.repositories;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class AutorRepository implements  Repositories<Autor, Long> {
    private final JdbcTemplate jdbcTemplate;

    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(Autor autor) {
        String sql = "INSERT INTO autores (name, ano_nascimento, ano_morte) VALUES (?, ?, ?) RETURNING id;";

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, autor.getNome());
                    ps.setInt(2, autor.getAnoNascimento());
                    ps.setInt(3, autor.getAnoMorte());
                    return ps;
                }
        );


        return 200L;
    }

    @Override
    public List<Autor> findAll() {
        String sql = "SELECT * FROM autores ORDER BY nome;";
        return jdbcTemplate.query(sql, (resultSet, i) -> getCliente(resultSet));
    }

    @Override
    public int update(Autor entity, Long sku) {
        return 0;
    }

    @Override
    public Autor findById(Long sku) {
        return null;
    }

    @Override
    public int delete(Long sku) {
        return 0;
    }


    public int delete(String nome, int anoNascimento) {
        String sql = "DELETE FROM autores WHERE nome = ? AND ano_nascimento = ?;";
        return jdbcTemplate.update(sql, nome, anoNascimento);
    }

    private Autor getCliente(ResultSet rs) throws SQLException {
        return Autor.builder()
                .nome(rs.getString("name"))
                .anoNascimento(rs.getInt("ano_nascimento"))
                .anoMorte(rs.getInt("ano_morte"))
                .build();
    }
}
