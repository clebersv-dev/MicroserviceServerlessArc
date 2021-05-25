package br.com.impacta.fullstack.saldoextrato.dto;

import java.util.List;

import br.com.impacta.fullstack.saldoextrato.domain.Investimento;

public class InvestimentoContaDTO {
	
	private Long idAccount;
	
	private String titular;
	
	private List<Investimento> investimento;
	
	public InvestimentoContaDTO(){}

	public InvestimentoContaDTO(String titular, Long id, List<Investimento> investimentos) {
		this.titular = titular;
		this.idAccount = id;
		this.investimento = investimentos;
	}

	public List<Investimento> getInvestimento() {
		return investimento;
	}

	public void setInvestimento(List<Investimento> investimento) {
		this.investimento = investimento;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}


	public String getTitular() {
		return titular;
	}


	public void setTitular(String titular) {
		this.titular = titular;
	} 

}
