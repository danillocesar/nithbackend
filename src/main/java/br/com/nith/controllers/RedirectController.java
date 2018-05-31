package br.com.nith.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RedirectController {
	
	@RequestMapping("/login")
	@CrossOrigin
	public String login() {
		return "login";
	}
}
