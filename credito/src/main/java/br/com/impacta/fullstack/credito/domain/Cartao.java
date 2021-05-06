package br.com.impacta.fullstack.credito.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.impacta.fullstack.credito.enums.TipoCartao;

@Entity(name = "TB_CARTAO")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;

	@Column(name = "TB_TITULAR")
	private String titular;

	@Column(name = "TB_NUMERO")
	private String numero;

	@Column(name = "TB_BANDEIRA")
	private String bandeira;

	@Column(name = "TB_SALDO")
	private BigDecimal saldo;
	
	@Column(name = "TB_LIMITE")
	private BigDecimal limite;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TIPO")
	private TipoCartao tipo;

	@OneToOne
	@JoinColumn(unique = true)
	private Cliente cliente;

	@OneToMany
	private List<Fatura> fatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TB_VALIDADE")
	private Date validade;
	
	private String novo;

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<Fatura> getFatura() {
		return fatura;
	}

	public void setFatura(List<Fatura> fatura) {
		this.fatura = fatura;
	}

	public TipoCartao getTipo() {
		return tipo;
	}

	public void setTipo(TipoCartao tipo) {
		this.tipo = tipo;
	}

	public String getNovo() {
		return novo;
	}

	public void setNovo(String novo) {
		this.novo = novo;
	}

}
