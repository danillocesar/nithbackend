package br.com.nith.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nith.models.ResponseDTO;
import br.com.nith.models.User;
import br.com.nith.services.UserService;
import br.com.nith.utils.SenhaInvalidaException;
import br.com.nith.utils.UsuarioExistenteException;
import br.com.nith.utils.UsuarioNaoEncontradoException;

@RestController
@CrossOrigin
public class UserController {

	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/api/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
		try {
			User user = userService.authenticate(username, password);
			ResponseDTO responseDto = new ResponseDTO(HttpStatus.OK);
			responseDto.setEntity(user);
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
		} catch(UsuarioNaoEncontradoException un){
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.UNAUTHORIZED, un.getMessage()), HttpStatus.OK);
		}catch(SenhaInvalidaException se){
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.UNAUTHORIZED, se.getMessage()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/api/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> signup(@RequestBody User user) {
		try {
			user = userService.signup(user);
			ResponseDTO responseDto = new ResponseDTO(HttpStatus.OK);
			responseDto.setEntity(user);
			return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
		} catch(UsuarioExistenteException un){
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.UNAUTHORIZED, un.getMessage()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
