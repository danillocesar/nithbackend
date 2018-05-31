package br.com.nith.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("httpSessionService")
public class HttpSessionService {

	@Autowired
	HttpSession httpSession;
	
}
