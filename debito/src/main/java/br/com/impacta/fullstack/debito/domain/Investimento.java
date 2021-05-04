package br.com.impacta.fullstack.debito.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.impacta.fullstack.debito.enums.TipoInvestimentos;

@Entity(name = "TB_INVESTIMENTO")
public class Investimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoInvestimentos tipo;
	@Column(name = "TB_SALDO")
	private BigDecimal saldo;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCompra;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRegaste;

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

}
