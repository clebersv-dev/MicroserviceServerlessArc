package br.com.impacta.fullstack.conta.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.impacta.fullstack.conta.domain.Endereco;
import br.com.impacta.fullstack.conta.enums.TipoCliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Long id;
	
	private String nome;

	private String cpfCnpj;
	
	@JsonIgnore
	private Date dataCadastro;

	private TipoCliente tipo;
	
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
