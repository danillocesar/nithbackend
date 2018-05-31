package br.com.nith.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token , Authorization");
        /*response.setHeader("Access-Control-Allow-Credentials", true);
        response.setHeader("Access-Control-Max-Age", 180);*/
        if ( request.getMethod().equals("OPTIONS") ) {
        	response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(request, response);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
