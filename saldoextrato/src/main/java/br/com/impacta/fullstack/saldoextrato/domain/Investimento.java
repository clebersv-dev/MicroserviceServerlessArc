package br.com.impacta.fullstack.saldoextrato.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.impacta.fullstack.saldoextrato.enums.TipoInvestimentos;

public class Investimento {

	private Long id;

	private TipoInvestimentos tipo;
	private BigDecimal valorUnitario;
	private Integer unidade;
	private BigDecimal saldo;
	private Calendar dataCompra;
	private Calendar dataRegaste;
	
	public Investimento() {
		super();
	}
	
	public Investimento(TipoInvestimentos tipo, BigDecimal valorUnitario, Integer unidade, BigDecimal saldo, Cliente cliente) {
		super();
		this.tipo = tipo;
		this.valorUnitario = valorUnitario;
		this.unidade = unidade;
		this.saldo = saldo;
		this.dataCompra = Calendar.getInstance();
	}
	
	public Investimento(Long id, TipoInvestimentos tipo, BigDecimal valorUnitario, Integer unidade, BigDecimal saldo,
			Calendar dataCompra, Calendar dataRegaste) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valorUnitario = valorUnitario;
		this.unidade = unidade;
		this.saldo = saldo;
		this.dataCompra = dataCompra;
		this.dataRegaste = dataRegaste;
	}
	
	public TipoInvestimentos getTipo() {
		return tipo;
	}

	public void setTipo(TipoInvestimentos tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Calendar getDataRegaste() {
		return dataRegaste;
	}

	public void setDataRegaste(Calendar dataRegaste) {
		this.dataRegaste = dataRegaste;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}
}
