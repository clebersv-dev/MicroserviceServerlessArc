package br.com.impacta.fullstack.conta.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class CartaoSaldoDTO extends CartaoDTO {
	private BigDecimal saldo;
	private Calendar validade;
	private String validadeStr;

	public String getValidade() {
		if (this.validade != null) {
			validadeStr = (1 + this.validade.get(Calendar.DAY_OF_MONTH)) 
				   + "/" + this.validade.get(Calendar.YEAR);
		}
		return validadeStr;
	}
	
	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public BigDecimal getDisponivelCompra() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
