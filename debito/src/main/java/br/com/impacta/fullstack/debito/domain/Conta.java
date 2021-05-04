package br.com.impacta.fullstack.debito.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "TB_CONTA")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Integer id;
	@Column(name = "TB_TITULAR")
	private String titular;
	@Column(name = "TB_BANCO")
	private String banco;
	@Column(name = "TB_AGENCIA")
	private String agencia;
	@Column(name = "TB_NUMERO")
	private String numero;
	@Column(name = "TB_SALDO")
	private BigDecimal saldo;
	@Column(name = "TB_LIMITE")
	private BigDecimal limite;

	@OneToOne
	@JoinColumn(unique = true)
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		if (cliente != null)
			this.titular = cliente.getNome();

		this.cliente = cliente;
	}

	public String getTitular() {
		return titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
}
