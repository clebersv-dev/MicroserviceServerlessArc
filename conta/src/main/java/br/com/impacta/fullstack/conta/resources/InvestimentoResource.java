package br.com.impacta.fullstack.conta.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impacta.fullstack.conta.domain.Conta;
import br.com.impacta.fullstack.conta.domain.Investimento;
import br.com.impacta.fullstack.conta.dto.InvestimentoContaDTO;
import br.com.impacta.fullstack.conta.dto.InvestimentoDTO;
import br.com.impacta.fullstack.conta.dto.RegasteDTO;
import br.com.impacta.fullstack.conta.dto.TipoInvestimentosDTO;
import br.com.impacta.fullstack.conta.enums.TipoInvestimentos;
import br.com.impacta.fullstack.conta.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.conta.services.ContaService;
import br.com.impacta.fullstack.conta.services.InvestimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/investimento/v1")
@Api(value = "Investimento")
public class InvestimentoResource {

	@Autowired
	private InvestimentoService service;
	
	@Autowired
	private ContaService serviceConta;

	@ApiOperation(value = "Returns Hello Investimento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> getInvestimentoHello() {
		return ResponseEntity.ok().body("Hello Investimento");
	}

	@ApiOperation(value = "Insert investiments into the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody InvestimentoDTO objDto) {
		Investimento obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Regaste investiments into the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/resgate/{id}" , method = RequestMethod.POST)
	public ResponseEntity<Void> regasteInvestiments(@Valid @RequestBody RegasteDTO objDto) {
		
		Conta account = serviceConta.findAccount(objDto.getIdAccount()); 
		service.resgateInvestimento(account, objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@ApiOperation(value = "Find investiments by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<InvestimentoContaDTO> findInvestiments(@PathVariable Long id) {
		Conta conta = serviceConta.findAccount(id);
		conta.setInvestimentos(
		conta.getInvestimentos().stream().filter(c -> c.getDataRegaste() == null).collect(Collectors.toList()));
		
		InvestimentoContaDTO  obj = new InvestimentoContaDTO(conta.getTitular(), conta.getId(), conta.getInvestimentos());
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Returns list of customer Investiments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<TipoInvestimentosDTO>> findInvestimentos() {
		
		List<TipoInvestimentosDTO> investimentos = Arrays.asList( TipoInvestimentos.values()).stream().map(tipo -> 
				new TipoInvestimentosDTO(tipo.toString(),  tipo.getValue())
				
		).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(investimentos);
	}
}
