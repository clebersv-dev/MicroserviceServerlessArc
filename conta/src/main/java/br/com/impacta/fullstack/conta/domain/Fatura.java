package br.com.impacta.fullstack.conta.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "TB_FATURA")
public class Fatura {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;
	@Column(name = "TB_LIMITE_TOTAL")
	private BigDecimal limiteTotal;
	
	@Column(name = "TB_LIMITE_UTILIZADO")
	private BigDecimal limiteUtilizado;
	@Column(name = "TB_LIMITE_DISPONIVEL")
	private BigDecimal disponivel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TB_DATA_TRANSACAO")
	private Calendar dataTransacao;

	public Fatura() {
		super();
	}
	
	public Fatura(BigDecimal limiteTotal, BigDecimal limiteUtilizado, BigDecimal disponivel) {
		super();
		this.limiteTotal = limiteTotal;
		this.limiteUtilizado = limiteUtilizado;
		this.disponivel = disponivel;
		this.dataTransacao = Calendar.getInstance();
	}
	
	public BigDecimal getLimiteTotal() {
		return limiteTotal;
	}
	public void setLimiteTotal(BigDecimal limiteTotal) {
		this.limiteTotal = limiteTotal;
	}
	public BigDecimal getLimiteUtilizado() {
		return limiteUtilizado;
	}
	public void setLimiteUtilizado(BigDecimal limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}
	public BigDecimal getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(BigDecimal disponivel) {
		this.disponivel = disponivel;
	}
	public Calendar getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
}
