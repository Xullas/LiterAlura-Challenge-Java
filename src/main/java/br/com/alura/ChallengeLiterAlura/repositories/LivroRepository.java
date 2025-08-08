package br.com.alura.ChallengeLiterAlura.repositories;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import br.com.alura.ChallengeLiterAlura.domain.Livro;
import br.com.alura.ChallengeLiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LivroRepository{

    @Autowired
    private AutorRepository autorRepository;

    private final JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void create(Livro livro){
        String sql = "INSERT INTO livros (id, titulo, numero_de_downloads) VALUES (?, ?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, livro.getId(), livro.getTitulo(), livro.getNumeroDownload());

        for (Autor autor : livro.getAutores()) {
            autorRepository.create(autor);
        }

        for (Autor autor: livro.getAutores()){
            String sqlLivroAutor = "INSERT INTO livro_autor (livro_id, autor_nome, autor_ano_nascimento) VALUES (?, ?, ?) ON CONFLICT DO NOTHING";
            jdbcTemplate.update(sqlLivroAutor, livro.getId(), autor.getNome(), autor.getAnoNascimento());
        }

        for(String resumo : livro.getResumo()){
            String sqlLivroResumo = "INSERT INTO livro_resumos (livro_id, resumo) VALUES (?, ?) ON CONFLICT DO NOTHING";
            jdbcTemplate.update(sqlLivroResumo, livro.getId(), resumo);
        }

        for(String idioma : livro.getIdiomas()){
            String sqlLivroIdioma = "INSERT INTO livro_idiomas (livro_id, idioma) VALUES (?, ?) ON CONFLICT DO NOTHING";
            jdbcTemplate.update(sqlLivroIdioma, livro.getId(), idioma);
        }
    }

    public List<Livro> findAll() {
        String sql = """
                SELECT
                l.id,
                l.titulo,
                l.numero_de_downloads,
                COALESCE(
                    (SELECT json_agg(json_build_object('name', a.nome, 'birth_year', a.ano_nascimento, 'death_year', a.ano_morte))
                    FROM livro_autor la JOIN autores a ON la.autor_nome = a.nome AND la.autor_ano_nascimento = a.ano_nascimento
                    WHERE la.livro_id = l.id),
                    '[]'::json
                ) AS "autores",
                COALESCE(
                    (SELECT json_agg(resumo) FROM livro_resumos WHERE livro_id = l.id),
                    '[]'::json
                ) AS "resumos",
                COALESCE(
                    (SELECT json_agg(idioma) FROM livro_idiomas WHERE livro_id = l.id),
                    '[]'::json
                ) AS "idiomas"
                FROM
                    livros l
                ORDER BY
                    l.id;""";
        return jdbcTemplate.query(sql, (resultSet, i) -> getLivro(resultSet));
    }

    public List<Livro> findByIdioma(String idioma) {
        String sql = """
                SELECT
                l.id,
                l.titulo,
                l.numero_de_downloads,
                COALESCE(
                    (SELECT json_agg(json_build_object('name', a.nome, 'birth_year', a.ano_nascimento, 'death_year', a.ano_morte))
                    FROM livro_autor la JOIN autores a ON la.autor_nome = a.nome AND la.autor_ano_nascimento = a.ano_nascimento
                    WHERE la.livro_id = l.id),
                    '[]'::json
                ) AS "autores",
                COALESCE(
                    (SELECT json_agg(resumo) FROM livro_resumos WHERE livro_id = l.id),
                    '[]'::json
                ) AS "resumos",
                COALESCE(
                    (SELECT json_agg(idioma) FROM livro_idiomas WHERE livro_id = l.id),
                    '[]'::json
                ) AS "idiomas"
                FROM
                    livros l
                 WHERE
                    EXISTS (
                        SELECT 1
                        FROM livro_idiomas li
                        WHERE li.livro_id = l.id AND li.idioma = ?
                        )
                ORDER BY
                    l.id;""";
        return jdbcTemplate.query(sql, (resultSet, i) -> getLivro(resultSet), idioma);
    }

    private Livro getLivro(ResultSet resultSet) throws SQLException {
        String autorJson = resultSet.getString("autores");
        String resumoJson = resultSet.getString("resumos");
        String idiomaJson = resultSet.getString("idiomas");

        List<Autor> autores = ConverteDados.obterLista(autorJson, Autor.class);
        List<String> resumos = ConverteDados.obterLista(resumoJson, String.class);
        List<String> idiomas = ConverteDados.obterLista(idiomaJson, String.class);

        return Livro.builder()
                .id(resultSet.getInt("id"))
                .titulo(resultSet.getString("titulo"))
                .numeroDownload(resultSet.getInt("numero_de_downloads"))
                .autores(autores)
                .resumo(resumos)
                .idiomas(idiomas)
                .build();
    }
}

