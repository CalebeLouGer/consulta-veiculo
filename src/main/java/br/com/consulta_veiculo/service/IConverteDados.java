package br.com.consulta_veiculo.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
