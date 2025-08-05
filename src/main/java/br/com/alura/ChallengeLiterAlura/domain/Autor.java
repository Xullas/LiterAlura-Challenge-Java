package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Autor {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private int anoNascimento;

    @JsonProperty("death_year")
    private int anoMorte;
}
