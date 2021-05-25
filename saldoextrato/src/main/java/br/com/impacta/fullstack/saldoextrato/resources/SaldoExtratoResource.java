package br.com.impacta.fullstack.saldoextrato.resources;

import java.util.ArrayList;
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

import br.com.impacta.fullstack.saldoextrato.domain.Conta;
import br.com.impacta.fullstack.saldoextrato.domain.Extrato;
import br.com.impacta.fullstack.saldoextrato.dto.CreditDTO;
import br.com.impacta.fullstack.saldoextrato.dto.DebitDTO;
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoContaDTO;
import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoDTO;
import br.com.impacta.fullstack.saldoextrato.dto.RegasteDTO;
import br.com.impacta.fullstack.saldoextrato.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.saldoextrato.exceptions.SaldoInsuficiente;
import br.com.impacta.fullstack.saldoextrato.services.SaldoExtratoService;
import feign.FeignException;

@RestController
@RequestMapping(value = "/saldoextrato")
public class SaldoExtratoResource {

	private static String SUCESSO = "Operação Efetuada com Sucesso!";
	private static String OBJNOTFOUND = "Object not found ";
	private static String NOBALANCE = "No balance available ";
	private List<Extrato> obj;

	@Autowired
	private SaldoExtratoService service;

	@HystrixCommand(fallbackMethod = "getAlternativeDebit", ignoreExceptions = { FeignException.NotFound.class,
			ObjectNotFoundException.class, FeignException.BadRequest.class, SaldoInsuficiente.class })
	@PostMapping(value = "/debito")
	public ResponseEntity<String> insert(@Valid @RequestBody DebitDTO objDto) {
		try {
			service.insert(objDto);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + objDto.getId());
		} catch (FeignException.BadRequest e) {
			throw new SaldoInsuficiente(NOBALANCE + objDto.getId());
		}
		return ResponseEntity.created(null).body(SUCESSO);
	}

	@HystrixCommand(fallbackMethod = "getAlternativeCredit", ignoreExceptions = { FeignException.NotFound.class,
			ObjectNotFoundException.class })
	@PostMapping(value = "/credito")
	public ResponseEntity<String> insert(@Valid @RequestBody CreditDTO objDto) {
		try {
			service.insert(objDto);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + objDto.getId());
		}
		return ResponseEntity.created(null).body(SUCESSO);
	}

	@HystrixCommand(fallbackMethod = "getAlternativeById", ignoreExceptions = { FeignException.NotFound.class,
			ObjectNotFoundException.class })
	@GetMapping(value = "/debito/{id}")
	public ResponseEntity<List<Extrato>> findDebitById(@PathVariable Long id) {
		try {
			obj = service.findExtratoById(id);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + id);
		}
		return ResponseEntity.ok().body(obj);
	}

	@HystrixCommand(fallbackMethod = "getAlternativeById", ignoreExceptions = { FeignException.NotFound.class,
			ObjectNotFoundException.class })
	@GetMapping(value = "/credito/{id}")
	public ResponseEntity<List<Extrato>> findCreditById(@PathVariable Long id) {
		ResponseEntity<List<Extrato>> obj;
		try {
			obj = service.findExtratoCreditById(id);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + id);
		}
		return ResponseEntity.ok().body(obj.getBody());
	}

	// ******Investimento******
	@HystrixCommand(fallbackMethod = "getAlternativeInvestimento", ignoreExceptions = { FeignException.NotFound.class,
			ObjectNotFoundException.class, FeignException.BadRequest.class, SaldoInsuficiente.class })
	@PostMapping(value = "/investimento")
	public ResponseEntity<String> insert(@Valid @RequestBody InvestimentoDTO objDto) {
		try {
			service.comprarInvestimento(objDto);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + objDto.toString());
		} catch (FeignException.BadRequest e) {
			throw new SaldoInsuficiente(NOBALANCE + objDto.toString());
		}
		return ResponseEntity.created(null).body(SUCESSO);
	}

	@HystrixCommand(fallbackMethod = "getAlternativeInvestimentoResgate", ignoreExceptions = {
			FeignException.NotFound.class, ObjectNotFoundException.class, FeignException.BadRequest.class,
			SaldoInsuficiente.class })
	@PostMapping(value = "/investimento/resgate")
	public ResponseEntity<String> regasteInvestiments(@Valid @RequestBody RegasteDTO objDto) {
		try {
			service.resgateInvestimento(objDto);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + objDto.toString());
		} catch (FeignException.BadRequest e) {
			throw new SaldoInsuficiente(NOBALANCE + objDto.toString());
		}
		return ResponseEntity.created(null).body(SUCESSO);
	}

	@HystrixCommand(fallbackMethod = "getAlternativeFindInvestimento", ignoreExceptions = {
			FeignException.NotFound.class, ObjectNotFoundException.class })
	@GetMapping(value = "/investimento/{id}")
	public ResponseEntity<InvestimentoContaDTO> findInvestiments(@PathVariable Long id) {
		ResponseEntity<InvestimentoContaDTO> obj;
		try {
			obj = service.findInvestimentos(id);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + id);
		}
		return ResponseEntity.ok().body(obj.getBody());
	}

	// Conta
	@HystrixCommand(fallbackMethod = "getAlternativeFindAcc", ignoreExceptions = { FeignException.NotFound.class, ObjectNotFoundException.class })
	@GetMapping(value = "/conta/{id}")
	public ResponseEntity<Conta> findAccount(@PathVariable Long id) {
		ResponseEntity<Conta> objCC;
		try {
			objCC = service.findAccount(id);
		} catch (FeignException.NotFound e) {
			throw new ObjectNotFoundException(OBJNOTFOUND + id);
		}
		return ResponseEntity.ok().body(objCC.getBody());
	}

	// Feign rotas alternativas
	public ResponseEntity<Conta> getAlternativeFindAcc(Long id) {
		return ResponseEntity.badRequest().body(new Conta());
	}
	
	public ResponseEntity<InvestimentoContaDTO> getAlternativeFindInvestimento(Long id) {
		return ResponseEntity.badRequest().body(new InvestimentoContaDTO());
	}

	public ResponseEntity<String> getAlternativeCredit(@RequestBody CreditDTO objDto) {
		return ResponseEntity.badRequest().body("Serviço de Crédito não disponivel, tente novamente mais tarde!");
	}

	public ResponseEntity<String> getAlternativeDebit(@RequestBody DebitDTO objDto) {
		return ResponseEntity.badRequest().body("Serviço de Débito não disponivel, tente novamente mais tarde!");
	}

	public ResponseEntity<String> getAlternativeDebit(@RequestBody InvestimentoDTO objDto) {
		return ResponseEntity.badRequest().body("Serviço de Investimento não disponivel, tente novamente mais tarde!");
	}

	public ResponseEntity<List<Extrato>> getAlternativeById(Long id) {
		return ResponseEntity.badRequest().body(new ArrayList<>());
	}

	public ResponseEntity<String> getAlternative() {
		return ResponseEntity.badRequest().body("Tente novamente mais tarde !!!");
	}

	// Feign rotas alternativas investimento
	public ResponseEntity<String> getAlternativeInvestimento(@Valid @RequestBody InvestimentoDTO objDto) {
		return ResponseEntity.badRequest().body("Serviço de Investimento não disponivel, tente novamente mais tarde!");
	}

	public ResponseEntity<String> getAlternativeInvestimentoResgate(@Valid @RequestBody RegasteDTO objDto) {
		return ResponseEntity.badRequest()
				.body("Serviço de Investimento/Resgate não disponivel, tente novamente mais tarde!");
	}

	// Hello verificacao de disponibilidade de MS
	@HystrixCommand(fallbackMethod = "getAlternative")
	@GetMapping(value = "/helloSaldoExtrato")
	public ResponseEntity<String> getHello() {
		return ResponseEntity.ok("Hello Saldo Extrato");
	}

	@HystrixCommand(fallbackMethod = "getAlternative")
	@GetMapping(value = "/helloDebito")
	public ResponseEntity<String> getHelloDebito() {
		ResponseEntity<String> hello = service.getHelloDebit();
		return ResponseEntity.ok(hello.getBody());
	}

	@HystrixCommand(fallbackMethod = "getAlternative")
	@GetMapping(value = "/helloCredito")
	public ResponseEntity<String> getHelloCredito() {
		ResponseEntity<String> hello = service.getHelloCredit();
		return ResponseEntity.ok(hello.getBody());
	}

	@HystrixCommand(fallbackMethod = "getAlternative")
	@GetMapping(value = "/helloInvestimento")
	public ResponseEntity<String> getHelloInvestimento() {
		ResponseEntity<String> hello = service.getHelloInvestimento();
		return ResponseEntity.ok(hello.getBody());
	}
}
