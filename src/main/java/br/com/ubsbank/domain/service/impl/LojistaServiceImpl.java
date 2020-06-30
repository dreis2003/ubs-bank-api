package br.com.ubsbank.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubsbank.api.dto.EstoqueLojistaDTO;
import br.com.ubsbank.api.dto.LojistaDTO;
import br.com.ubsbank.domain.entity.Estoque;
import br.com.ubsbank.domain.service.EstoqueService;
import br.com.ubsbank.domain.service.LojistaService;

@Service
public class LojistaServiceImpl implements LojistaService {

	@Autowired
	private EstoqueService estoqueService;

	@Override
	public Stream<LojistaDTO> calcularVenda(String produto, int qtdeLojistas) {
		Stream<Estoque> estoque = estoqueService.getEstoquebyProduto(produto);
		Stream<Estoque> estoquep = estoqueService.getEstoquebyProduto(produto);

		int qtdEstoque = estoquep.mapToInt(Estoque::getQuantity).sum();

		//TODO - Validar esta condição
//		if (qtdEstoque % qtdeLojistas != 0) {
//			return null;
//		}
		
		List<LojistaDTO> lojistas = new ArrayList<>();
		
		for(int i=0; i< qtdeLojistas; i++) { 
			lojistas.add(new LojistaDTO());
		}
		
		AtomicInteger resto = new AtomicInteger();
		AtomicBoolean hasNext = new AtomicBoolean(false);
		
		estoque.forEach( p -> {
            int qtdLojistas =0;

            if(p.getQuantity() % qtdeLojistas ==0) {
                for(int i =0; i < qtdeLojistas; i++) {
                    EstoqueLojistaDTO estoqueLojistaDTO = new EstoqueLojistaDTO();
                    estoqueLojistaDTO.setQuantity(p.getQuantity() / qtdeLojistas);
                    estoqueLojistaDTO.setPrice(Double.valueOf(p.getPrice().replace("$","")));
                    estoqueLojistaDTO.setVolume(estoqueLojistaDTO.getPrice() * estoqueLojistaDTO.getQuantity());
                    lojistas.get(i).getEstoqueLogistaDTO().add(estoqueLojistaDTO);
                }
            } else {
                resto.set(p.getQuantity() % qtdeLojistas);
                if(!hasNext.get()) {
                    qtdLojistas = p.getQuantity() / qtdeLojistas;

                    for (int i = 0; i < qtdeLojistas - 1; i++) {
                        EstoqueLojistaDTO estoqueLojistaDTO = new EstoqueLojistaDTO();

                        estoqueLojistaDTO.setQuantity(qtdLojistas);
                        estoqueLojistaDTO.setPrice(Double.valueOf(p.getPrice().replace("$","")));
                        estoqueLojistaDTO.setVolume(estoqueLojistaDTO.getPrice() * estoqueLojistaDTO.getQuantity());
                        lojistas.get(i).getEstoqueLogistaDTO().add(estoqueLojistaDTO);
                    }

                    EstoqueLojistaDTO estoqueLojistaDTO = new EstoqueLojistaDTO();

                    estoqueLojistaDTO.setQuantity(qtdLojistas + resto.intValue());
                    estoqueLojistaDTO.setPrice(Double.valueOf(p.getPrice().replace("$","")));
                    estoqueLojistaDTO.setVolume(estoqueLojistaDTO.getPrice() * estoqueLojistaDTO.getQuantity());
                    lojistas.get(qtdeLojistas - 1).getEstoqueLogistaDTO().add(estoqueLojistaDTO);

                    hasNext.set(true);

                } else {
                    qtdLojistas = (p.getQuantity() + resto.intValue()) / qtdeLojistas;

                    for (int i = 0; i < qtdeLojistas - 1; i++) {

                        EstoqueLojistaDTO estoqueLojistaDTO = new EstoqueLojistaDTO();

                        estoqueLojistaDTO.setQuantity(qtdLojistas);
                        estoqueLojistaDTO.setPrice(Double.valueOf(p.getPrice().replace("$","")));
                        estoqueLojistaDTO.setVolume(estoqueLojistaDTO.getPrice() * estoqueLojistaDTO.getQuantity());
                        lojistas.get(i).getEstoqueLogistaDTO().add(estoqueLojistaDTO);
                    }

                    EstoqueLojistaDTO estoqueLojistaDTO = new EstoqueLojistaDTO();

                    estoqueLojistaDTO.setQuantity(qtdLojistas - resto.intValue());
                    estoqueLojistaDTO.setPrice(Double.valueOf(p.getPrice().replace("$","")));
                    estoqueLojistaDTO.setVolume(estoqueLojistaDTO.getPrice() * estoqueLojistaDTO.getQuantity());
                    lojistas.get(qtdeLojistas - 1).getEstoqueLogistaDTO().add(estoqueLojistaDTO);

                    hasNext.set(false);
                }
            }


        });
		
		lojistas.stream().forEach(lojista -> {

            lojista.setQtde(lojista
            					.getEstoqueLogistaDTO()
            					.stream()
            					.mapToInt(EstoqueLojistaDTO::getQuantity)
            					.sum());

            lojista.setPrecoMedio(lojista
            						.getEstoqueLogistaDTO()
            						.stream()
            						.mapToDouble(EstoqueLojistaDTO::getVolume)
            						.sum() / lojista.getQtde());


            lojista.setFinanceiro(lojista
            						.getEstoqueLogistaDTO()
            						.stream()
            						.mapToDouble(EstoqueLojistaDTO::getVolume)
            						.sum());
        });

        return lojistas.stream();
	}

}
