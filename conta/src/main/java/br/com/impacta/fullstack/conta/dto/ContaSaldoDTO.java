package br.com.impacta.fullstack.conta.dto;

import java.math.BigDecimal;

public class ContaSaldoDTO extends ContaDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8234200600911195549L;
	private BigDecimal saldo;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
}