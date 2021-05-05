package br.com.impacta.fullstack.conta.dto;

import java.math.BigDecimal;

public class CartaoUpdateDTO {
	
	private Long id;
	
	private BigDecimal limite;

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
