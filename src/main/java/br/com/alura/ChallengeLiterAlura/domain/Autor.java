package br.com.alura.ChallengeLiterAlura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private int anoMorte;
}
