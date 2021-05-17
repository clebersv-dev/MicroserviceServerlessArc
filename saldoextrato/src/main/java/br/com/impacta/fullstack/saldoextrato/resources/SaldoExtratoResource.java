package br.com.impacta.fullstack.saldoextrato.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.impacta.fullstack.saldoextrato.domain.Extrato;
import br.com.impacta.fullstack.saldoextrato.dto.CreditDTO;
import br.com.impacta.fullstack.saldoextrato.dto.DebitDTO;
import br.com.impacta.fullstack.saldoextrato.services.SaldoExtratoService;

@RestController
@RequestMapping(value = "/saldoextrato")
public class SaldoExtratoResource {

	@Autowired
	private SaldoExtratoService service;

	@HystrixCommand(fallbackMethod = "getHelloAlternative")
	@GetMapping(value = "/helloSaldoExtrato")
	public ResponseEntity<String> getHello(){
		return ResponseEntity.ok("Hello Saldo Extrato");
	}
	
	@HystrixCommand(fallbackMethod = "getHelloAlternative")
	@GetMapping(value = "/helloDebito")
	public ResponseEntity<String> getHelloDebito(){
		ResponseEntity<String> hello = service.getHelloDebit();
		return ResponseEntity.ok(hello.getBody());
	}
	
	@HystrixCommand(fallbackMethod = "getHelloAlternative")
	@GetMapping(value = "/helloCredito")
	public ResponseEntity<String> getHelloCredito(){
		ResponseEntity<String> hello = service.getHelloCredit();
		return ResponseEntity.ok(hello.getBody());
	}
	
//	@HystrixCommand(fallbackMethod = "getAlternativeVoid")
	@PostMapping(value = "/debito")
	public ResponseEntity<Void> insert(@Valid @RequestBody DebitDTO objDto) {
		service.insert(objDto);
		return ResponseEntity.noContent().build();
	}
	
//	@HystrixCommand(fallbackMethod = "getAlternativeVoid")
	@PostMapping(value = "/credito")
	public ResponseEntity<Void> insert(@Valid @RequestBody CreditDTO objDto) {
		service.insert(objDto);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/debito/{id}")
	public ResponseEntity<List<Extrato>> findDebitById(@PathVariable Long id) {
		List<Extrato> obj = service.findExtratoById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/credito/{id}")
	public ResponseEntity<List<Extrato>> findCreditById(@PathVariable Long id) {
		List<Extrato> obj = service.findExtratoCreditById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	public ResponseEntity<String> getHelloAlternative(){
		return ResponseEntity.ok("Tente novamente mais tarde !!!");
	}
	
	public ResponseEntity<Void> getAlternativeVoid(){
		return ResponseEntity.noContent().build();
	}
}
