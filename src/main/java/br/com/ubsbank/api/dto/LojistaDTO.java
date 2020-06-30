package br.com.ubsbank.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LojistaDTO {
	
	private List<EstoqueLojistaDTO> estoqueLogistaDTO = new ArrayList<>();
	private int qtde;
    private double financeiro;
    private double precoMedio;

}
