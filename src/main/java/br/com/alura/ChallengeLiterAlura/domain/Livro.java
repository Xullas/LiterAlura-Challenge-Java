package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {

    private int id;

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("authors")
    private List<Autor> autores;

    @JsonProperty("summaries")
    private List<String> resumo;

    @JsonProperty("languages")
    private List<String> idiomas;
}
