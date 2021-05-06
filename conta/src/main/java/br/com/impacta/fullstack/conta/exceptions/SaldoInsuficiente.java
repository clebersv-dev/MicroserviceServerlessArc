package br.com.impacta.fullstack.conta.exceptions;

public class SaldoInsuficiente extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficiente(String msg) {
		super(msg);
	}
	
	public SaldoInsuficiente(String msg, Throwable cause) {
		super(msg, cause);
	}

}
