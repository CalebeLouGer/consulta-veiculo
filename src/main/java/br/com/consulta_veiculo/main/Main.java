package br.com.consulta_veiculo.main;

import br.com.consulta_veiculo.model.DadosVeiculo;
import br.com.consulta_veiculo.model.Modelos;
import br.com.consulta_veiculo.model.Veiculo;
import br.com.consulta_veiculo.service.ConsumoApi;
import br.com.consulta_veiculo.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        var opcaoVeiculo = scanner.nextLine();
        String endereco;
        if(opcaoVeiculo.toLowerCase().contains("car")){
            endereco = URL + "carros/marcas";
        }else if(opcaoVeiculo.toLowerCase().contains("mot")){
            endereco = URL + "motos/marcas";
        }else {
            endereco = URL + "caminhoes/marcas";
        }

        var json = consumoApi.obterDados(endereco);
        System.out.println("===================================================");
        System.out.println("Dados dos Veículos: ");
        var marcas = converteDados.obterLista(json, DadosVeiculo.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosVeiculo::nome))
                .forEach(System.out::println);

        System.out.println("Digite o Código para ver as MARCAS: ");
        var opcaoModelos = scanner.nextLine();
        endereco = endereco + "/" + opcaoModelos + "/modelos";
        json = consumoApi.obterDados(endereco);
        var modeloLista = converteDados.obterDados(json, Modelos.class);

        System.out.println("===================================================");
        System.out.println("Dados dos Modelos: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculo::nome))
                .forEach(System.out::println);


        System.out.println("Digite o Nome do Veículo: ");
        var nomeVeiculo = scanner.nextLine();

        List<DadosVeiculo> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("===================================================");
        System.out.println("Modelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o Código do ANO: ");
        var codigoAno = scanner.nextLine();
        endereco = endereco + "/" + codigoAno + "/anos";
        json = consumoApi.obterDados(endereco);
        List<DadosVeiculo> anos = converteDados.obterLista(json, DadosVeiculo.class);

        List<Veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAnos);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("===================================================");
        System.out.println("Modelos Filtrados Por Ano: ");
        veiculos.forEach(System.out::println);


    }
}