package br.com.alura.ChallengeLiterAlura.service;

import br.com.alura.ChallengeLiterAlura.domain.Livro;
import br.com.alura.ChallengeLiterAlura.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public void createLivro(Livro livro) {
        livroRepository.create(livro);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findByIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
