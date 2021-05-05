package br.com.impacta.fullstack.debito.resources;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.fullstack.debito.domain.Conta;
import br.com.impacta.fullstack.debito.domain.Extrato;
import br.com.impacta.fullstack.debito.dto.DebitDTO;
import br.com.impacta.fullstack.debito.services.DebitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/debit/v1")
@Api(value = "Debit")
public class DebitResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(DebitResource.class);

	@Autowired
	private DebitService service;

	@ApiOperation(value = "Returns Hello Debit")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> getHelloDebit() {
		return ResponseEntity.ok().body("Hello Debit");
	}
	
	@ApiOperation(value = "Update an object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DebitDTO objDto) {
		Conta cc = service.insert(objDto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Returns an Extrato object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Extrato>> findDebitById(@PathVariable Long id) {
		List<Extrato> obj = service.findExtratoById(id);
		return ResponseEntity.ok().body(obj);
	}
}
