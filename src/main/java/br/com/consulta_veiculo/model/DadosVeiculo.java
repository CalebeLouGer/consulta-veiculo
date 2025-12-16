package br.com.consulta_veiculo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(String codigo, String nome) {

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | (Código: " + codigo + ") ";
    }
}
