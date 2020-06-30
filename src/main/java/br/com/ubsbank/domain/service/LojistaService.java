package br.com.ubsbank.domain.service;

import java.util.stream.Stream;

import br.com.ubsbank.api.dto.LojistaDTO;

public interface LojistaService {
	
	Stream<LojistaDTO> calcularVenda(String produto, int qtdeLojistas);

}
