package br.com.ubsbank.domain.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubsbank.domain.entity.Estoque;
import br.com.ubsbank.domain.repository.EstoqueRepository;
import br.com.ubsbank.domain.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;

	@Override
	public Stream<Estoque> getEstoquebyProduto(String produto) {
		return ((List<Estoque>) estoqueRepository.findAll()).stream().filter(p -> p.getProduct().equals(produto));
	}

}
