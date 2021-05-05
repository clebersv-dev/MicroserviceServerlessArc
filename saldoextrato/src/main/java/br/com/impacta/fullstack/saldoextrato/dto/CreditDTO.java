package br.com.impacta.fullstack.saldoextrato.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreditDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal creditValue;
	
	public CreditDTO() {
	}

	public CreditDTO(Long id, BigDecimal creditValue) {
		super();
		this.id = id;
		this.creditValue = creditValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return creditValue;
	}

	public void setValue(BigDecimal creditValue) {
		this.creditValue = creditValue;
	}	
}
