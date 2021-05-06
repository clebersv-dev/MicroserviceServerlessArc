package br.com.impacta.fullstack.saldoextrato.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public class DebitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal debitValue;
	
	public DebitDTO() {
	}

	public DebitDTO(Long id, BigDecimal debitValue) {
		super();
		this.id = id;
		this.debitValue = debitValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getDebitValue() {
		return debitValue;
	}

	public void setDebitValue(BigDecimal debitValue) {
		this.debitValue = debitValue;
	}
}
