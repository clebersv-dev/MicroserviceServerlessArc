package br.com.impacta.fullstack.saldoextrato.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.impacta.fullstack.saldoextrato.domain.Extrato;
import br.com.impacta.fullstack.saldoextrato.dto.DebitDTO;

@Component
@FeignClient(name = "debito", path = "/debit/v1/")
public interface DebitoFeignClient {

	@GetMapping(value = "/hello")
	ResponseEntity<String> getHelloDebit();
	
	@PostMapping
	ResponseEntity<Void> insert(DebitDTO obj);
	
	@GetMapping(value = "/{id}")
	List<Extrato> findExtratoById(Long id);
	
}
