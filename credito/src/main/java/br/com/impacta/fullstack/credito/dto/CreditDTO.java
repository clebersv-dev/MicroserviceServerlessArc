package br.com.impacta.fullstack.credito.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import br.com.impacta.fullstack.credito.domain.Credit;



public class CreditDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Mandatory filling")
	private String description;

	private BigDecimal creditValue;
	
	public CreditDTO() {
	}
	
	public CreditDTO(Credit obj) {
		id = obj.getId();
		description = obj.getDescription();
		creditValue = obj.getCredit();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return creditValue;
	}

	public void setValue(BigDecimal creditValue) {
		this.creditValue = creditValue;
	}	
}
