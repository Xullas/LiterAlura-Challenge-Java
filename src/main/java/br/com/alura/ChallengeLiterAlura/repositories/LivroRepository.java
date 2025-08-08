package br.com.alura.ChallengeLiterAlura.repositories;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import br.com.alura.ChallengeLiterAlura.domain.Livro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LivroRepository implements Repositories<Livro, Long> {

    private final JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public void create(Livro livro){
        String sql = "INSERT INTO livros (id, titulo) VALUES (?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, livro.getId(), livro.getTitulo());

        for (Autor autor : livro.getAutores()) {
            String sqlAutor = "INSERT INTO autores (nome, ano_nascimento, ano_morte) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlAutor, autor.getNome(), autor.getAnoNascimento(), autor.getAnoMorte());
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

