package br.com.alura.ChallengeLiterAlura.service;

import br.com.alura.ChallengeLiterAlura.domain.Livro;
import br.com.alura.ChallengeLiterAlura.repositories.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public void createLivro(Livro livro) {
        livroRepository.create(livro);
    }

}
