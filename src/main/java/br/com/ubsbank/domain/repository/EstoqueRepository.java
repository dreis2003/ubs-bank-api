package br.com.ubsbank.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ubsbank.domain.entity.Estoque;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long>{

}
