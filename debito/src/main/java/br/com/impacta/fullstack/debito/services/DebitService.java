package br.com.impacta.fullstack.debito.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.debito.domain.Conta;
import br.com.impacta.fullstack.debito.domain.Extrato;
import br.com.impacta.fullstack.debito.dto.DebitDTO;
import br.com.impacta.fullstack.debito.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.debito.repository.ContaRepository;
import br.com.impacta.fullstack.debito.repository.ExtratoRepository;

@Service
public class DebitService {

	@Autowired
	private ContaRepository repoAcc;
	
	@Autowired
	private ExtratoRepository repoExt;

	public List<Extrato> findExtratoById(Long id) {
		Optional<List<Extrato>> obj = Optional.of(repoExt.findAllById(Arrays.asList(id)));
		return obj.orElseThrow(() -> new ObjectNotFoundException(Extrato.class.getName()));
	}
	
	public Conta insert(DebitDTO obj) {
		Conta cc = this.findAccount(obj.getId());
		
		this.createExtrato(cc, obj);
		
		return repoAcc.save(cc);
	}
	

	private void createExtrato(Conta cc, DebitDTO obj) {
		cc.withDraw(obj.getDebitValue());
	}


	public Conta findAccount(Long id) {
		Optional<Conta> obj = repoAcc.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Conta.class.getName()));
	}
}
