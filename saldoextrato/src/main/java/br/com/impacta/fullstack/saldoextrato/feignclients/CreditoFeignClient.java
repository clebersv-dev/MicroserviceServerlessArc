package br.com.impacta.fullstack.saldoextrato.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "credito", path = "/credit/v1/")
public interface CreditoFeignClient {

	@GetMapping(value = "/hello")
	ResponseEntity<String> getHelloCredit();
}
