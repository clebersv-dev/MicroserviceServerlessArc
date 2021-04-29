package br.com.impacta.fullstack.saldoextrato.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.fullstack.saldoextrato.services.SaldoExtratoService;

@RestController
@RequestMapping(value = "/saldoextrato")
public class SaldoExtratoResource {

	@Autowired
	private SaldoExtratoService service;
	
//	@HystrixCommand(fallbackMethod = "getDebitoAlternative")
	@GetMapping(value = "/debito")
	public ResponseEntity<String> getHelloDebit(){
		ResponseEntity<String> hello = service.getHelloDebit();
		return ResponseEntity.ok(hello.getBody());
	}
	
//	@HystrixCommand(fallbackMethod = "getCreditAlternative")
	@GetMapping(value = "/credito")
	public ResponseEntity<String> getHelloCredit(){
		ResponseEntity<String> hello = service.getHelloCredit();
		return ResponseEntity.ok(hello.getBody());
	}
}
