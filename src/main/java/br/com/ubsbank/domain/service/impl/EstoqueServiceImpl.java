package br.com.ubsbank.domain.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubsbank.domain.entity.Estoque;
import br.com.ubsbank.domain.repository.EstoqueRepository;
import br.com.ubsbank.domain.service.EstoqueService;
import br.com.ubsbank.infra.json.JsonReader;

@Service
public class EstoqueServiceImpl implements EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private JsonReader processor;

	@Override
	public Stream<Estoque> getEstoquebyProduto(String produto) {
		return ((List<Estoque>) estoqueRepository.findAll()).stream().filter(p -> p.getProduct().equals(produto));
	}

	@Override
	public void loadJsonFiles() throws IOException {
		if(!estoqueRepository.findAll().iterator().hasNext()) {
			estoqueRepository.saveAll(processor.read("json_files/data_1.json"));
			estoqueRepository.saveAll(processor.read("json_files/data_2.json"));
			estoqueRepository.saveAll(processor.read("json_files/data_3.json"));
			estoqueRepository.saveAll(processor.read("json_files/data_4.json"));
			System.out.println("Carga completa dos arquivos!!!");
		} else {
			System.out.println("Sem dados para carregar.");
		}
	}

}
