package br.com.ubsbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ubsbank.domain.repository.EstoqueRepository;
import br.com.ubsbank.infra.json.JsonReader;

@SpringBootApplication
public class UbsBankApiApplication implements CommandLineRunner {
	
	@Autowired
	private EstoqueRepository estoqueRespository;
	
	@Autowired
	private JsonReader processor;

	public static void main(String[] args) {
		SpringApplication.run(UbsBankApiApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {

		if(!estoqueRespository.findAll().iterator().hasNext()) {
			estoqueRespository.saveAll(processor.read("json_files/data_1.json"));
			estoqueRespository.saveAll(processor.read("json_files/data_2.json"));
			estoqueRespository.saveAll(processor.read("json_files/data_3.json"));
			estoqueRespository.saveAll(processor.read("json_files/data_4.json"));
			System.out.println("Carga completa dos arquivos!!!");
		} else {
			System.out.println("Sem dados para carregar.");
		}
	}
	
	

}
