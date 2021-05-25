//package br.com.impacta.fullstack.saldoextrato.feignclients;
//
//import javax.validation.Valid;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoContaDTO;
//import br.com.impacta.fullstack.saldoextrato.dto.InvestimentoDTO;
//import br.com.impacta.fullstack.saldoextrato.dto.RegasteDTO;
//
//@Component
//@FeignClient(name = "conta", path = "/investimento/v1/")
//public interface InvestimentoFeignClient {
//
//	@GetMapping(value = "/hello")
//	ResponseEntity<String> getInvestimentoHello();
//	
//	@PostMapping
//	ResponseEntity<String> insert(@Valid @RequestBody InvestimentoDTO objDto);
//	
//	@PostMapping(value = "/resgate")
//	ResponseEntity<String> regasteInvestiments(@Valid @RequestBody RegasteDTO objDto);
//	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<InvestimentoContaDTO> findInvestiments(@PathVariable Long id);
//}
