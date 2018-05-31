package br.com.nith.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nith.dao.UserDao;
import br.com.nith.models.User;
import br.com.nith.services.UserService;
import br.com.nith.utils.SenhaInvalidaException;
import br.com.nith.utils.UsuarioExistenteException;
import br.com.nith.utils.UsuarioNaoEncontradoException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User authenticate(String username, String password) throws SenhaInvalidaException, UsuarioNaoEncontradoException {
		User user = userDao.getByUsername(username);
		if(user == null){
			throw new UsuarioNaoEncontradoException();
		}else if(password.equals(user.getPassword())){
			//log user
			return user;
		}else{
			throw new SenhaInvalidaException();
		}
		
	}

	@Override
	public User signup(User user) throws UsuarioExistenteException{
		if(userDao.getByUsername(user.getEmail()) != null){
			throw new UsuarioExistenteException();
		}
		userDao.insert(user);
		user = userDao.getByUsername(user.getEmail());
		return user;
	}
}
