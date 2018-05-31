package br.com.nith.utils;


public class SenhaInvalidaException extends Exception {
	private static final long serialVersionUID = -3202264981328159633L;

	// Parameterless Constructor
	public SenhaInvalidaException() {
		super(ResponseMessages.SENHA_INVALIDA.toString());
	}

	// Constructor that accepts a message
	public SenhaInvalidaException(String message) {
		super(message);
	}
}
