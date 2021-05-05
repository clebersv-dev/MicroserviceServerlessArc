package br.com.impacta.fullstack.credito.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.credito.domain.Conta;
import br.com.impacta.fullstack.credito.domain.Extrato;
import br.com.impacta.fullstack.credito.dto.CreditDTO;
import br.com.impacta.fullstack.credito.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.credito.repository.ContaRepository;
import br.com.impacta.fullstack.credito.repository.ExtratoRepository;

@Service
public class CreditService {
	
	@Autowired
	private ContaRepository repoAcc;
	
	@Autowired
	private ExtratoRepository repoExt;

//	public Credit findCreditById(Long id) {
//		Optional<Credit> obj = repo.findById(id);
//		return obj.orElseThrow(() -> new ObjectNotFoundException(
//				"object not found! Id: " + id + ", Type: " + Credit.class.getName()));
//	}
	
	public List<Extrato> findExtratoById(Long id) {
		Optional<List<Extrato>> obj = Optional.of(repoExt.findAllById(Arrays.asList(id)));
		return obj.orElseThrow(() -> new ObjectNotFoundException(Extrato.class.getName()));
	}
	
	public Conta insert(CreditDTO obj) {
		Conta cc = this.findAccount(obj.getId());
		
		this.createExtrato(cc, obj);
		
		return repoAcc.save(cc);
	}
	

	private void createExtrato(Conta cc, CreditDTO obj) {
		cc.deposit(obj.getCreditValue());
	}


	public Conta findAccount(Long id) {
		Optional<Conta> obj = repoAcc.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Conta.class.getName()));
	}

//	public List<Credit> findAllCreditById(Long id) {
//		Optional<List<Credit>> obj = Optional.of(repo.findAllById(Arrays.asList(id)));
//		return obj.orElseThrow(() -> new ObjectNotFoundException(
//				"object not found! Id: " + id + ", Type: " + Credit.class.getName()));
//	}
}
