package br.com.impacta.fullstack.saldoextrato.dto;

public class RegasteDTO {
	
	private Long idAccount;
	private Long idInvestiments ; 
	
	public Long getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}
	public Long getIdInvestiments() {
		return idInvestiments;
	}
	public void setIdInvestiments(Long idInvestiments) {
		this.idInvestiments = idInvestiments;
	}
	@Override
	public String toString() {
		return "Regaste [Conta=" + idAccount + ", Investimento=" + idInvestiments + "]";
	}
}
