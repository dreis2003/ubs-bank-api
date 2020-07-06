package br.com.ubsbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ubsbank.domain.service.EstoqueService;

@SpringBootApplication
public class UbsBankApiApplication implements CommandLineRunner {
	
	@Autowired
	private EstoqueService estoqueService;
	
	public static void main(String[] args) {
		SpringApplication.run(UbsBankApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		estoqueService.loadJsonFiles();
	}
	
	

}
