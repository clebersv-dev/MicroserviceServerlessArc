package br.com.impacta.fullstack.conta.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.impacta.fullstack.conta.enums.TipoOperacao;

@Entity(name = "TB_EXTRATO")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_OPERACAO")
	private TipoOperacao operacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TB_DATA")
	private Date data;

	@Column(name = "TB_VALOR")
	private BigDecimal valor;

	public Extrato() {
		super();
	}
	
	public Extrato(TipoOperacao operacao, BigDecimal valor) {
		super();
		this.operacao = operacao;
		this.data = new Date();
		this.valor = valor;
	}
	
	public TipoOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacao operacao) {
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
