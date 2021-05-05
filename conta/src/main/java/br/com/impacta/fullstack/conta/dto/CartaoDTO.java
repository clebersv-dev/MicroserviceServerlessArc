package br.com.impacta.fullstack.conta.dto;

import java.math.BigDecimal;

import br.com.impacta.fullstack.conta.enums.TipoCartao;

public class CartaoDTO {
	
	private String titular;
	private String numero;
	private String bandeira;
	private BigDecimal limite;
	private TipoCartao tipo;
	private Long idCliente;

	public CartaoDTO() {
	
	}

	public CartaoDTO(String titular, String numero, String bandeira, BigDecimal saldo, BigDecimal limite,
			TipoCartao tipo, Long idCliente) {
		this.titular = titular;
		this.numero = numero;
		this.bandeira = bandeira;
		this.limite = limite;
		this.tipo = tipo;
		this.idCliente = idCliente;
	}

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

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public TipoCartao getTipo() {
		return tipo;
	}

	public void setTipo(TipoCartao tipo) {
		this.tipo = tipo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}
