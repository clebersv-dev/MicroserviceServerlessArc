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
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoContaDTO;
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoDTO;
import br.com.impacta.fullstack.saldoextrato.dto.RegasteDTO;
import br.com.impacta.fullstack.saldoextrato.feignclients.ContaFeignClient;
import br.com.impacta.fullstack.saldoextrato.feignclients.CreditoFeignClient;
import br.com.impacta.fullstack.saldoextrato.feignclients.DebitoFeignClient;

@Service
public class SaldoExtratoService {

	@Autowired
	private CreditoFeignClient creditoFeignClient;
	
	@Autowired
	private DebitoFeignClient debitoFeignClient;
	
	@Autowired
	private ContaFeignClient contaFeignClient;
	
	//******Debito******
	public ResponseEntity<String> insert(@Valid DebitDTO obj) {
		return debitoFeignClient.insert(obj);
	}
	
	public List<Extrato> findExtratoById(Long id) {
		return debitoFeignClient.findExtratoById(id);
	}
	
	//******Credito******
	public ResponseEntity<String> insert(@Valid CreditDTO objDto) {
		return creditoFeignClient.insert(objDto);
	}

	public ResponseEntity<List<Extrato>> findExtratoCreditById(Long id) {
		return creditoFeignClient.findCreditById(id);
	}
	
	//******Investimento******
	public void comprarInvestimento(@Valid InvestimentoDTO objDto) {
		contaFeignClient.insert(objDto);
	}
	
	public void resgateInvestimento(@Valid RegasteDTO objDto) {
		contaFeignClient.regasteInvestiments(objDto);
	}

	//Hello
	public ResponseEntity<String> getHelloDebit(){
		return debitoFeignClient.getHelloDebit();
	}

	public ResponseEntity<String> getHelloCredit() {
		return creditoFeignClient.getHelloCredit();
	}
	
	public ResponseEntity<String> getHelloInvestimento() {
		return contaFeignClient.getInvestimentoHello();
	}

	public ResponseEntity<InvestimentoContaDTO> findInvestimentos(Long id) {
		return contaFeignClient.findInvestiments(id);
	}

	public ResponseEntity<Conta> findAccount(Long id) {
		return contaFeignClient.findAccount(id);
	}

}
