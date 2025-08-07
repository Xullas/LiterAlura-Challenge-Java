package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosBusca {

    @JsonProperty("results")
    private List<Livro> livros;

}
