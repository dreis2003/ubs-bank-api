package br.com.ubsbank.api.resources;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ubsbank.api.dto.LojistaDTO;
import br.com.ubsbank.domain.service.LojistaService;

@RestController("/estoque")
@RequestMapping(value = "/estoque")
public class EstoqueResource {
	
	@Autowired
	private LojistaService logistaService;
	
	 @GetMapping("/vender/{produto}/{qtdLojistas}")
	    public Stream<LojistaDTO> vender(@PathVariable String produto, @PathVariable int qtdLojistas){
	        return logistaService.calcularVenda(produto, qtdLojistas); 
	    }

}
