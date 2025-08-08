package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@With
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

    @JsonProperty("download_count")
    private int numeroDownload;

    public void imprimirDados(){
        System.out.println("----- LIVRO -----");
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Autor:  " + this.autores.stream().findFirst().map(Autor::getNome).orElse(""));
        System.out.println("Idioma:  " + this.idiomas.stream().findFirst().orElse(""));
        System.out.println("Número de Downloads:  " + this.numeroDownload);
        System.out.println("-----------------\n");
    }
}
