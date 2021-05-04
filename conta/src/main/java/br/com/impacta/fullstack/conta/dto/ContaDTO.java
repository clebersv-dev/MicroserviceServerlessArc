package br.com.impacta.fullstack.conta.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String titular;

	private String banco;

	private String agencia;

	private String numero;
	
	private BigDecimal saldo;

	private BigDecimal limite;

	private ClienteDTO cliente;

	public ContaDTO() {
		super();
	}
	
	public ContaDTO(Integer id, String titular, String banco, String agencia, String numero, BigDecimal saldo,
			BigDecimal limite, ClienteDTO cliente) {
		super();
		this.id = id;
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
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

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
}
