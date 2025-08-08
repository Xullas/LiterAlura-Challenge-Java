package br.com.alura.ChallengeLiterAlura.repositories;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import br.com.alura.ChallengeLiterAlura.service.ConverteDados;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AutorRepository {

    private final JdbcTemplate jdbcTemplate;

    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void create(Autor autor){
            String sqlAutor = "INSERT INTO autores (nome, ano_nascimento, ano_morte) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlAutor, autor.getNome(), autor.getAnoNascimento(), autor.getAnoMorte());
    }

    public List<Autor> findAll() {
        String sql = """
                SELECT
                    a.nome,
                    a.ano_nascimento,
                    a.ano_morte,
                    COALESCE(
                        (SELECT json_agg(l.titulo)
                         FROM livros l
                         JOIN livro_autor la ON l.id = la.livro_id
                         WHERE la.autor_nome = a.nome AND la.autor_ano_nascimento = a.ano_nascimento
                        ),
                        '[]'::json
                    ) AS titulos_dos_livros
                FROM
                    autores a
                ORDER BY
                    a.nome;""";
        return jdbcTemplate.query(sql, (resultSet, i) -> getAutorComLivros(resultSet));
    }

    public List<Autor> autoresVivosDeterminadoAno(Long ano) {
        String sql = """
                SELECT
                    a.nome,
                    a.ano_nascimento,
                    a.ano_morte,
                    COALESCE(
                        (SELECT json_agg(l.titulo)
                         FROM livros l
                         JOIN livro_autor la ON l.id = la.livro_id
                         WHERE la.autor_nome = a.nome AND la.autor_ano_nascimento = a.ano_nascimento
                        ),
                        '[]'::json
                    ) AS titulos_dos_livros
                FROM
                    autores a
                WHERE
                    a.ano_nascimento <= ? AND (a.ano_morte >= ? OR a.ano_morte IS NULL OR a.ano_morte = 0)
                ORDER BY
                    a.nome;""";
        return jdbcTemplate.query(sql, (resultSet, i) -> getAutorComLivros(resultSet), ano, ano);
    }

    private Autor getAutor(ResultSet resultSet) throws SQLException {
        String titulosJson = resultSet.getString("titulos_dos_livros");

        List<String> listaDeTitulos  = ConverteDados.obterLista(titulosJson, String.class);

        return Autor.builder()
                .nome(resultSet.getString("nome"))
                .anoNascimento(resultSet.getInt("ano_nascimento"))
                .anoMorte((Integer) resultSet.getObject("ano_morte"))
                .titulosDosLivros(listaDeTitulos)
                .build();
    }

    private Autor getAutorComLivros(ResultSet resultSet) throws SQLException {
        Autor autor = getAutor(resultSet);

        String titulosJson = resultSet.getString("titulos_dos_livros");
        List<String> listaDeTitulos  = ConverteDados.obterLista(titulosJson, String.class);

        return autor.withTitulosDosLivros(listaDeTitulos);
    }
}

