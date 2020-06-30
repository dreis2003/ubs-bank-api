package br.com.ubsbank.api.dto;

import lombok.Data;

@Data
public class EstoqueLojistaDTO {

	private int quantity;
	private double price;
    private double volume;
    
}
