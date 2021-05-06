package br.com.impacta.fullstack.credito.resources;

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

import br.com.impacta.fullstack.credito.domain.Cartao;
import br.com.impacta.fullstack.credito.domain.Conta;
import br.com.impacta.fullstack.credito.domain.Extrato;
import br.com.impacta.fullstack.credito.dto.CreditDTO;
import br.com.impacta.fullstack.credito.services.CreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@RefreshScope
@RestController
@RequestMapping(value = "/credit/v1")
@Api(value = "Credit")
public class CreditResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreditResource.class);

	@Autowired
	private CreditService service;

	@ApiOperation(value = "Returns Hello Credit")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> getHelloCredit() {
		return ResponseEntity.ok().body("Hello Credit");
	}

	@ApiOperation(value = "Update an object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CreditDTO objDto) {
		Conta cc = service.insert(objDto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Update an object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/cartao", method = RequestMethod.POST)
	public ResponseEntity<Void> insertCreditCard(@Valid @RequestBody CreditDTO objDto) {
		Cartao cartao = service.insertCreditCard(objDto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Returns an Extrato object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Extrato>> findCreditById(@PathVariable Long id) {
		List<Extrato> obj = service.findExtratoById(id);
		return ResponseEntity.ok().body(obj);
	}
}
