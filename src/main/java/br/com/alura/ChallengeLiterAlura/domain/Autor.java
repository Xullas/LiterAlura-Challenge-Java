package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@With
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("birth_year")
    private int anoNascimento;

    @JsonProperty("death_year")
    private Integer anoMorte;

    private List<String> titulosDosLivros;


    public void imprimirDados(){
        System.out.println("----- AUTOR -----");
        System.out.println("Nome: " + this.nome);
        System.out.println("Ano de nascimento:  " + this.anoNascimento);
        System.out.println("Ano de falecimento:  " + this.anoMorte);
        System.out.println("Livros:  " + this.titulosDosLivros);
        System.out.println("-----------------\n");
    }
}
