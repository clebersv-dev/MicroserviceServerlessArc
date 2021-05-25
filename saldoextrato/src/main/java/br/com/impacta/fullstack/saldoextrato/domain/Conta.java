package br.com.impacta.fullstack.saldoextrato.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.impacta.fullstack.saldoextrato.enums.TipoSegmento;

public class Conta {

	private Long id;
	private String titular;
	private String banco;
	private String agencia;
	private String numero;
	private BigDecimal saldo;
	private BigDecimal limite;
	private TipoSegmento segmento;

	private List<Investimento> investimentos = new ArrayList<>();

	private List<Extrato> extrato = new ArrayList<>();

	private Cliente cliente;

	public Conta() {
		this.saldo = BigDecimal.ZERO;
		this.limite = BigDecimal.ZERO;
	}
	
	public Conta(Long id, String titular, String banco, String agencia, String numero, BigDecimal saldo,
			BigDecimal limite, TipoSegmento segmento, List<Investimento> investimentos, List<Extrato> extrato,
			Cliente cliente) {
		super();
		this.id = id;
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo == null ? BigDecimal.ZERO : saldo;
		this.limite = limite == null ? BigDecimal.ZERO : limite;
		this.segmento = segmento;
		this.investimentos = investimentos;
		this.extrato = extrato;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public TipoSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(TipoSegmento segmento) {
		this.segmento = segmento;
	}

	public List<Investimento> getInvestimentos() {
		return investimentos;
	}

	public void setInvestimentos(List<Investimento> investimentos) {
		this.investimentos = investimentos;
	}

	public List<Extrato> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<Extrato> extrato) {
		this.extrato = extrato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
