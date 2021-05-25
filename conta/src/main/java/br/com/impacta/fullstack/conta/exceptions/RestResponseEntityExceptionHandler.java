package br.com.impacta.fullstack.conta.exceptions;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<DefaultError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		LOG.error(e.getMessage());
		DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(SaldoInsuficiente.class)
	public ResponseEntity<DefaultError> saldoInsuficiente(SaldoInsuficiente e, HttpServletRequest request) {
		LOG.error(e.getMessage());
		DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ValorDeveSerMaiorQueZero.class)
	public ResponseEntity<DefaultError> valorDeveSerMaiorQueZero(ValorDeveSerMaiorQueZero e, HttpServletRequest request) {
		LOG.error(e.getMessage());
		DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DefaultError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		LOG.error(e.getMessage());
		
		ValidatorErrors error = new ValidatorErrors(HttpStatus.BAD_REQUEST.value(), "Error the validatition", System.currentTimeMillis());
	
		e.getBindingResult()
			.getFieldErrors()
			.forEach( x -> error.addError(x.getField(), x.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


}