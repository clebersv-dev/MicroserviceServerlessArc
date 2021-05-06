package br.com.impacta.fullstack.credito.exceptions;

public class ValorDeveSerMaiorQueZero extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValorDeveSerMaiorQueZero(String msg) {
		super(msg);
	}
	
	public ValorDeveSerMaiorQueZero(String msg, Throwable cause) {
		super(msg, cause);
	}

}
