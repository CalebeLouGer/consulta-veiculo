package br.com.consulta_veiculo;

import br.com.consulta_veiculo.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaVeiculoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ConsultaVeiculoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main principal = new Main();
        principal.exibirMenu();
    }
}
