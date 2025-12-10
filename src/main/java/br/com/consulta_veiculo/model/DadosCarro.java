package br.com.consulta_veiculo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCarro(@JsonAlias("codigo") Integer codigo,
                         @JsonAlias("nome") String nome) {

    @Override
    public String toString() {
        return "DadosCarro{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
