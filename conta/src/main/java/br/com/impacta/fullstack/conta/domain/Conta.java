package br.com.impacta.fullstack.conta.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.impacta.fullstack.conta.enums.TipoSegmento;
import br.com.impacta.fullstack.conta.exceptions.SaldoInsuficiente;

@Entity(name = "TB_CONTA")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;
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
	@Column(name = "TB_SEGMENTO")
	@Enumerated(EnumType.STRING)
	private TipoSegmento segmento;
	
	@OneToMany
	private List<Investimento> investimentos = new ArrayList<>();
	
	@OneToMany
	private List<Extrato> extrato = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(unique = true)
	private Cliente cliente;
	
	public Conta() {
		this.saldo = BigDecimal.ZERO;
		this.limite = BigDecimal.ZERO;
	}
	
	public Conta(String titular, String banco, String agencia, String numero, BigDecimal saldo,
			BigDecimal limite, Cliente cliente, TipoSegmento segmento) {
		super();
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo == null ? BigDecimal.ZERO : saldo;
		this.limite = limite == null ? BigDecimal.ZERO: limite;
		this.cliente = cliente;
		this.segmento = segmento;
	}
	
	public Conta(String titular, String banco, String agencia, String numero, BigDecimal saldo,
			BigDecimal limite, Cliente cliente, TipoSegmento segmento, Investimento investimento) {
		super();
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo == null ? BigDecimal.ZERO : saldo;
		this.limite = limite == null ? BigDecimal.ZERO: limite;
		this.cliente = cliente;
		this.segmento = segmento;
		this.investimentos.add(investimento);
	}
	
	public Conta(Long id, String titular, String banco, String agencia, String numero, BigDecimal saldo,
			BigDecimal limite) {
		super();
		this.id = id;
		this.titular = titular;
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
	}
	
    public void withDraw(BigDecimal value) {
        validateValue(value);

        BigDecimal ret = this.saldo.subtract(value);
        if (ret.compareTo(this.limite.negate()) <= -1) {
            throw new SaldoInsuficiente("no balance available");
        }
        this.setSaldo(saldo.subtract(value));
    }

    public void deposit(BigDecimal value) {
        validateValue(value);

        this.setSaldo(this.saldo.add(value));
    }
    
    private void validateValue(BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SaldoInsuficiente("value should be greater than zero");
        }
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

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		if (cliente != null)
			this.titular = cliente.getNome();

		this.cliente = cliente;
	}
	
	public List<Extrato> getExtrato() {
		return extrato;
	}

	public void setExtrato(Extrato extrato) {
		this.extrato.add(extrato);
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

	public void setInvestimentos(Investimento investimento) {
		this.investimentos.add(investimento);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((limite == null) ? 0 : limite.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limite == null) {
			if (other.limite != null)
				return false;
		} else if (!limite.equals(other.limite))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}
}
