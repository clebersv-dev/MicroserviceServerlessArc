package br.com.impacta.fullstack.conta.dto;

import java.math.BigDecimal;

public class TipoInvestimentosDTO {

	private String descricao;
	private BigDecimal value;

	public TipoInvestimentosDTO(String descricao, BigDecimal value) {
		this.descricao = descricao;
		this.value = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
