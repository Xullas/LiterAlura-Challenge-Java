package br.com.alura.ChallengeLiterAlura.service;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import br.com.alura.ChallengeLiterAlura.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public List<Autor> autoresVivosDeterminadoAno(Long ano) {
        return autorRepository.autoresVivosDeterminadoAno(ano);
    }
}
