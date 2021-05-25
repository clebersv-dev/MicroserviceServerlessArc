package br.com.impacta.fullstack.saldoextrato.feignclients;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.impacta.fullstack.saldoextrato.domain.Extrato;
import br.com.impacta.fullstack.saldoextrato.dto.CreditDTO;

@Component
@FeignClient(name = "credito", path = "/credit/v1")
public interface CreditoFeignClient {

	@GetMapping(value = "/hello")
	ResponseEntity<String> getHelloCredit();
	
	@PostMapping
	ResponseEntity<String> insert(@Valid CreditDTO objDto);
	
	@GetMapping(value = "/{id}")
	ResponseEntity<List<Extrato>> findCreditById(@PathVariable Long id);
}
