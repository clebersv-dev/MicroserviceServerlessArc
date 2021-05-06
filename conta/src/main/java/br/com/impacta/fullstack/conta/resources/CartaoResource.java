package br.com.impacta.fullstack.conta.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impacta.fullstack.conta.domain.Cartao;
import br.com.impacta.fullstack.conta.domain.Fatura;
import br.com.impacta.fullstack.conta.dto.CartaoDTO;
import br.com.impacta.fullstack.conta.dto.CartaoSaldoDTO;
import br.com.impacta.fullstack.conta.dto.CartaoUpdateDTO;
import br.com.impacta.fullstack.conta.services.CartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cartao/v1")
@Api(value = "Cartao")
public class CartaoResource {

	@Autowired
	private CartaoService service;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Returns Hello Conta")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> getCartHello() {
		return ResponseEntity.ok().body("Hello Cartao");
	}

	@ApiOperation(value = "Insert cart into the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CartaoDTO objDto) {
		Cartao fromDTO = service.fromDTO(objDto);
		Fatura fat = service.insert(new Fatura());
		fromDTO.addFatura(fat);
		Cartao obj = service.insert(fromDTO);
		obj.setSaldo(BigDecimal.ZERO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Update cart into the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CartaoUpdateDTO objDto) {
		Cartao cartao = modelMapper.map(objDto, Cartao.class);
		Cartao obj = service.update(cartao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Returns an Cart object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
	public ResponseEntity<CartaoSaldoDTO> find(@PathVariable Long id) {
		Cartao obj = service.find(id);
		CartaoSaldoDTO cartaoDto = modelMapper.map(obj, CartaoSaldoDTO.class);
		return ResponseEntity.ok().body(cartaoDto);
	}

	@ApiOperation(value = "Returns an Client object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "You do not have permission to access this resource ((Unauthorized))"),
			@ApiResponse(code = 403, message = "You do not have permission to access this resource"),
			@ApiResponse(code = 500, message = "an exception was thrown"), })
	@RequestMapping(value = "/carts", method = RequestMethod.GET)
	public ResponseEntity<List<CartaoSaldoDTO>> findAllCarts() {
		List<Cartao> obj = service.findAll();

		List<CartaoSaldoDTO> listDto = modelMapper.map(obj, new TypeToken<List<CartaoSaldoDTO>>() {
		}.getType());
		return ResponseEntity.ok().body(listDto);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
