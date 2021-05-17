package br.com.impacta.fullstack.saldoextrato.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.saldoextrato.domain.Conta;
import br.com.impacta.fullstack.saldoextrato.domain.Extrato;
import br.com.impacta.fullstack.saldoextrato.dto.CreditDTO;
import br.com.impacta.fullstack.saldoextrato.dto.DebitDTO;
import br.com.impacta.fullstack.saldoextrato.feignclients.CreditoFeignClient;
import br.com.impacta.fullstack.saldoextrato.feignclients.DebitoFeignClient;

@Service
public class SaldoExtratoService {

	@Autowired
	private CreditoFeignClient creditoFeignClient;
	
	@Autowired
	private DebitoFeignClient debitoFeignClient;
	
	public ResponseEntity<String> getHelloDebit(){
		return debitoFeignClient.getHelloDebit();
	}

	public ResponseEntity<String> getHelloCredit() {
		return creditoFeignClient.getHelloCredit();
	}
	
	public ResponseEntity<Void> insert(DebitDTO obj) {
		return (ResponseEntity<Void>) debitoFeignClient.insert(obj);
	}

	public List<Extrato> findExtratoById(Long id) {
		return debitoFeignClient.findExtratoById(id);
	}

	public Conta insert(@Valid CreditDTO objDto) {
		return creditoFeignClient.insert(objDto);
	}

	public List<Extrato> findExtratoCreditById(Long id) {
		return creditoFeignClient.findExtratoById(id);
	}
}
