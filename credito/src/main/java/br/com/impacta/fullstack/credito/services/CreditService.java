package br.com.impacta.fullstack.credito.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.credito.domain.Cartao;
import br.com.impacta.fullstack.credito.domain.Conta;
import br.com.impacta.fullstack.credito.domain.Extrato;
import br.com.impacta.fullstack.credito.domain.Fatura;
import br.com.impacta.fullstack.credito.dto.CreditDTO;
import br.com.impacta.fullstack.credito.enums.TipoOperacao;
import br.com.impacta.fullstack.credito.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.credito.exceptions.SaldoInsuficiente;
import br.com.impacta.fullstack.credito.repository.CartaoRepository;
import br.com.impacta.fullstack.credito.repository.ContaRepository;
import br.com.impacta.fullstack.credito.repository.ExtratoRepository;
import br.com.impacta.fullstack.credito.repository.FaturaRepository;

@Service
public class CreditService {
	
	@Autowired
	private ContaRepository repoAcc;
	
	@Autowired
	private ExtratoRepository repoExt;
	
	@Autowired
	private CartaoRepository repoCard;

	@Autowired
	private FaturaRepository repoFat;
	
	public List<Extrato> findExtratoById(Long id) {
		Optional<List<Extrato>> obj = Optional.of(repoExt.findAllById(Arrays.asList(id)));
		return obj.orElseThrow(() -> new ObjectNotFoundException(Extrato.class.getName()));
	}
	
	public Conta insert(CreditDTO obj) {
		Conta cc = this.findAccount(obj.getId());
		
		this.createExtrato(cc, obj);
		
		return repoAcc.save(cc);
	}
	
	public Cartao insertCreditCard(CreditDTO obj) {
		Cartao cartao = this.findCard(obj.getId());
		
		this.createFatura(cartao, obj);
		
		return repoCard.save(cartao);
	}

	private void createFatura(Cartao cartao, CreditDTO obj) {
		Fatura fatura = cartao.getFatura().get(0);
		
		if((fatura.getDisponivel() != null && fatura.getDisponivel().compareTo(BigDecimal.ZERO) >= 0) || fatura.getDisponivel() == null) {
			fatura.setDataTransacao(Calendar.getInstance());
			fatura.setLimiteTotal(cartao.getLimite());
			fatura.setLimiteUtilizado(obj.getCreditValue());
			fatura.setDisponivel(cartao.withDraw(cartao.getLimite(), fatura.getLimiteUtilizado()));
			return;
		}
		
		throw new SaldoInsuficiente("Saldo insuficiente");
	}

	private void createExtrato(Conta cc, CreditDTO obj) {
		cc.deposit(obj.getCreditValue());
		
		Extrato ext = repoExt.save(new Extrato(TipoOperacao.CREDITO, obj.getCreditValue()));
		cc.setExtrato(ext);
	}

	public Conta findAccount(Long id) {
		Optional<Conta> obj = repoAcc.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Conta.class.getName()));
	}
	
	public Cartao findCard(Long id) {
		Optional<Cartao> obj = repoCard.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Cartao.class.getName()));
	}
}
