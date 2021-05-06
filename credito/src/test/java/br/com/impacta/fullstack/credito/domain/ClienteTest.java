package br.com.impacta.fullstack.credito.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import br.com.impacta.fullstack.credito.enums.TipoCartao;
import br.com.impacta.fullstack.credito.enums.TipoCliente;

public class ClienteTest {

	final String BANDEIRA_MASTER = "Master Card";
	
	@Test
	public void createClientSucess() {
		Cliente clie = this.mockCliente();
		Conta acc = this.mockAcc(clie);
		
		assertEquals("Cleber", clie.getNome());
		assertEquals("41548896584", clie.getCpfCnpj());
		assertNotNull(clie.getEndereco());
		assertEquals(TipoCliente.FISICO, clie.getTipo());
	}
	
	@Test
	public void createAccountSucess() {
		Cliente clie = this.mockCliente();
		Conta acc = this.mockAcc(clie);
	}
	
	@Test
	public void createCreditCard() {
		Cartao cartao = this.createCard();
	}
	
	private Cliente mockCliente() {
		return new Cliente("Cleber", "41548896584", this.mockEndereco(), TipoCliente.FISICO);
	}
	
	private Conta mockAcc(Cliente clie) {
		return new Conta(clie.getNome(), "237", "0001", "09659440", new BigDecimal(1000.00), new BigDecimal(200.00));
	}

	private Endereco mockEndereco() {
		return new Endereco("09360-660", "Rua Joaquim Pereira dos Santos", "", "Vila Assis Brasil", "Mauá", "SP", "3529401", "4420", "11", "6689");
	}



	public void objectCreateTest() {
		Cartao cartao = this.createCard();
		
		assertEquals(1L, cartao.getId());
		assertEquals("Cleber", cartao.getTitular());
		assertEquals("001122004565", cartao.getNumero());
		assertEquals(BANDEIRA_MASTER, cartao.getBandeira());
		assertEquals(new BigDecimal(1000.00), cartao.getSaldo());
		assertEquals(new BigDecimal(-200), cartao.getLimite());
		assertEquals(TipoCartao.CARTAO_CREDITO , cartao.getTipo());
		assertNotNull(cartao.getCliente());
		assertTrue(cartao.getFatura().isEmpty());
	}

	private Cartao createCard() {
		Cartao cartao = new Cartao();
		cartao.setId(1L);
		cartao.setTitular("Cleber");
		cartao.setNumero("001122004565");
		cartao.setBandeira("Master Card");
		cartao.setSaldo(new BigDecimal(1000.00));
		cartao.setLimite(new BigDecimal(-200));
		cartao.setTipo(TipoCartao.CARTAO_CREDITO);
		cartao.setCliente(this.mockCliente());
		cartao.setFatura(new ArrayList<Fatura>());
		return cartao;
	}
}
