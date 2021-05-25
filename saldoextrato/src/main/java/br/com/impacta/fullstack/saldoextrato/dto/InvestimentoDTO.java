package br.com.impacta.fullstack.saldoextrato.dto;

import java.math.BigDecimal;

import br.com.impacta.fullstack.saldoextrato.enums.TipoInvestimentos;

public class InvestimentoDTO {
	
	private Long idCliente;
	private TipoInvestimentos tipo;
	private Integer quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal total;
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setSaldo(BigDecimal total) {
		this.total = total;
	}
	public TipoInvestimentos getTipo() {
		return tipo;
	}
	public void setTipo(TipoInvestimentos tipo) {
		this.tipo = tipo;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	@Override
	public String toString() {
		return "InvestimentoDTO [idCliente=" + idCliente + ", idInvestimento=" + tipo + "]";
	}
}

