package br.com.ubsbank.domain.service;

import java.io.IOException;
import java.util.stream.Stream;

import br.com.ubsbank.domain.entity.Estoque;


public interface EstoqueService {

	Stream<Estoque> getEstoquebyProduto(String produto);
	
	void loadJsonFiles() throws IOException;
	
}
