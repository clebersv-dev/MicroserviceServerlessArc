package br.com.impacta.fullstack.saldoextrato.feignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.impacta.fullstack.saldoextrato.domain.Conta;
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoContaDTO;
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoDTO;
import br.com.impacta.fullstack.saldoextrato.dto.RegasteDTO;

@Component
@FeignClient(name = "conta")
public interface ContaFeignClient {

	@GetMapping(value = "/conta/v1/{id}")
	ResponseEntity<Conta> findAccount(@PathVariable Long id);
	
	@GetMapping(value = "/investimento/v1/hello")
	ResponseEntity<String> getInvestimentoHello();
	
	@PostMapping("/investimento/v1/")
	ResponseEntity<String> insert(@Valid @RequestBody InvestimentoDTO objDto);
	
	@PostMapping(value = "/investimento/v1/resgate")
	ResponseEntity<String> regasteInvestiments(@Valid @RequestBody RegasteDTO objDto);
	
	@GetMapping(value = "/investimento/v1/{id}")
	public ResponseEntity<InvestimentoContaDTO> findInvestiments(@PathVariable Long id);
}
