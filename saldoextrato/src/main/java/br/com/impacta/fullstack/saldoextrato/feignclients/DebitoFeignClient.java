package br.com.impacta.fullstack.saldoextrato.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "debito", path = "/debit/v1/")
public interface DebitoFeignClient {

	@GetMapping(value = "/hello")
	ResponseEntity<String> getHelloDebit();
}
