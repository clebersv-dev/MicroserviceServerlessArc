package br.com.impacta.fullstack.debito.dto;

import java.io.Serializable;
import java.math.BigDecimal;



public class DebitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal creditValue;
	
	public DebitDTO() {
	}

	public DebitDTO(Long id, BigDecimal creditValue) {
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

	public BigDecimal getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(BigDecimal creditValue) {
		this.creditValue = creditValue;
	}
}
