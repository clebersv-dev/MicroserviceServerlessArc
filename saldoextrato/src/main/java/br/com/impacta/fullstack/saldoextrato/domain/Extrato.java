package br.com.impacta.fullstack.saldoextrato.domain;

import java.math.BigDecimal;
import java.util.Date;

import br.com.impacta.fullstack.saldoextrato.enums.Operacao;

public class Extrato {

	private Long id;

	private Operacao operacao;

	private Date data;

	private BigDecimal valor;

	public Extrato() {
		super();
	}
	
	public Extrato(Operacao operacao, BigDecimal valor) {
		super();
		this.operacao = operacao;
		this.data = new Date();
		this.valor = valor;
	}
	
	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
