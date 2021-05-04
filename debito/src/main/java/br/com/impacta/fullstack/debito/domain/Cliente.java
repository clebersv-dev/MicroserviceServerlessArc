package br.com.impacta.fullstack.debito.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.impacta.fullstack.debito.enums.TipoCliente;

@Entity(name = "TB_CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7939948716384858094L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TB_ID")
	private Long id;
	
    @OneToMany(mappedBy="cliente")
    private List<Conta> conta = new ArrayList<>();

	@Column(name = "TB_NOME", columnDefinition = "varchar(140)", nullable = false)
	private String nome;

	@Column(name = "TB_CPF_CNPJ", columnDefinition = "varchar(14)", nullable = false)
	private String cpfCnpj;
	
	@Embedded
	@Column(name = "Endereco", columnDefinition = "varchar(150)", nullable = false)
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	@Column(name = "TB_TIPO")
	private TipoCliente tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TB_DATA_CADASTRO")
	private Calendar dataCadastro;

	public Cliente() {}
	
	public Cliente(String nome, String cpfCnpj, Calendar dataCadastro, Endereco endereco, Conta conta, TipoCliente tipo) {
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.conta.add(conta);
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	public List<Conta> getConta() {
		return conta;
	}
	
	public void addConta(Conta conta) {
		this.conta.add(conta);
	}
	
	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}
	
}
