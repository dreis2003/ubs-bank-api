package br.com.ubsbank.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {
	
    @Id
    @GeneratedValue
    private Long id;
    
    private String product;

    private int quantity;

    private String price;

    private String type;

    private String industry;

    private String origin;
    

}
