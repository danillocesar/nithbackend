package br.com.nith.config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException ex) throws IOException, ServletException{
    response.setStatus(HttpStatus.FORBIDDEN.value());

    Map<String, Object> data = new HashMap<>();
    data.put("timestamp", new Date());
    data.put("status",HttpStatus.FORBIDDEN.value());
    data.put("message", "Access Denied");
    data.put("path", request.getRequestURL().toString());

    OutputStream out = response.getOutputStream();
    com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, data);
    out.flush();
  }
}
