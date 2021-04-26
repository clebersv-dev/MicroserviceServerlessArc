package br.com.impacta.fullstack.saldoextrato.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
