package br.com.nith.services;

import br.com.nith.models.User;
import br.com.nith.utils.SenhaInvalidaException;
import br.com.nith.utils.UsuarioExistenteException;
import br.com.nith.utils.UsuarioNaoEncontradoException;



public interface UserService {
	User authenticate(String username, String password) throws SenhaInvalidaException, UsuarioNaoEncontradoException;
	User signup(User user) throws UsuarioExistenteException;
}
