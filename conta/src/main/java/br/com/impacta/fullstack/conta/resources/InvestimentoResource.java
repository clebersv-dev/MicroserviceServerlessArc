package br.com.impacta.fullstack.conta.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impacta.fullstack.conta.domain.Investimento;
import br.com.impacta.fullstack.conta.dto.InvestimentoDTO;
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
	private ModelMapper modelMapper;

	@ApiOperation(value = "Returns Hello Investimento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> getCartHello() {
		return ResponseEntity.ok().body("Hello Investimento");
	}

	@ApiOperation(value = "Insert cart into the database")
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
	
	@ApiOperation(value = "Find investiments by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/investments/{id}", method = RequestMethod.GET)
	public ResponseEntity<Investimento> findInvestiments(@PathVariable Long id) {
		Investimento investimento = service.findOneInvestiments(id);
		return ResponseEntity.ok().body(investimento);
	}
	
	@ApiOperation(value = "Returns list of customer Investiments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/investments/list/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Investimento>> findInvestimentos(@PathVariable Long id) {
		List<Investimento> investimentos = service.findAllInvestiments(id);
		return ResponseEntity.ok().body(investimentos);
	}
}
