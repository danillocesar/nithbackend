package br.com.nith.utils;


public class UsuarioNaoEncontradoException extends Exception {
	private static final long serialVersionUID = -3202264981328159633L;

	// Parameterless Constructor
	public UsuarioNaoEncontradoException() {
		super(ResponseMessages.USUARIO_INVALIDO.toString());
	}

	// Constructor that accepts a message
	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}
}
