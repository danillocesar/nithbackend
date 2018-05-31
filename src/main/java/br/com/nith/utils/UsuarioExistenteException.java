package br.com.nith.utils;


public class UsuarioExistenteException extends Exception {
	private static final long serialVersionUID = -3202264981328159633L;

	// Parameterless Constructor
	public UsuarioExistenteException() {
		super(ResponseMessages.USUARIO_EXISTENTE.toString());
	}

	// Constructor that accepts a message
	public UsuarioExistenteException(String message) {
		super(message);
	}
}
