package br.com.impacta.fullstack.debito.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import br.com.impacta.fullstack.debito.domain.Debit;



public class DebitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Mandatory filling")
	private String description;

	private BigDecimal debitValue;
	
	public DebitDTO() {
	}
	
	public DebitDTO(Debit obj) {
		id = obj.getId();
		description = obj.getDescription();
		debitValue = obj.getDebit();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDebitValue() {
		return debitValue;
	}

	public void setDebitValue(BigDecimal debitValue) {
		this.debitValue = debitValue;
	}
}
