package br.com.impacta.fullstack.conta.domain;

import javax.persistence.ManyToMany;

public class ContaCorrente extends Conta {

	private String seguimento;
	
	@ManyToMany
	private Investimento investimento;

	public String getSeguimento() {
		return seguimento;
	}

	public void setSeguimento(String seguimento) {
		this.seguimento = seguimento;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}
}