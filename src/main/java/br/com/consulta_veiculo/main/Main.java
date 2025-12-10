package br.com.consulta_veiculo.main;

import br.com.consulta_veiculo.model.DadosCarro;
import br.com.consulta_veiculo.service.ConsumoApi;
import br.com.consulta_veiculo.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    public void exibirMenu(){
        System.out.println("===================================================");
        System.out.println("Seja Bem-Vindo ao ConsultaVeículo");
        String mensagemOpcao = String.format("""
                ===================================================
                Digite uma das Opções abaixo:
                [1] Carro
                [2] Moto
                [3] Caminhão
                ===================================================
                """);
        System.out.println(mensagemOpcao);
        var opcao = scanner.nextLine();
        String endereco;
        if(opcao.toLowerCase().contains("car")){
            endereco = URL + "carros/marcas";
        }else if(opcao.toLowerCase().contains("mot")){
            endereco = URL + "motos/marcas";
        }else {
            endereco = URL + "caminhoes/marcas";
        }
        var json = consumoApi.obterDados(endereco);
        System.out.println(json);

        var dadosCarro = converteDados.obterDados(json, DadosCarro.class);
        System.out.println(dadosCarro);

    }
}