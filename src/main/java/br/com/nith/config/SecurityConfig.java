package br.com.nith.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.IgnoredRequestCustomizer;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.context.request.RequestAttributes;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(authProvider);
		auth.userDetailsService(userDetailsService);
	}
	@Bean
    CORSFilter corsFilter() {
		CORSFilter filter = new CORSFilter();
        return filter;
    }
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/api/authenticate", "/api/**");
	}
    protected void configure(HttpSecurity http) throws Exception {
    	/*http
        .addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
        .authorizeRequests()
        .antMatchers("/api**")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/api/authenticate")
        .permitAll()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .anyRequest().authenticated();*/
    	
    	 http
         .addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
         .authorizeRequests()                                                                
    	 .anyRequest().authenticated()
    	 .and().formLogin().loginPage("/login").permitAll()
    	 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//We don't need sessions to be created.;
    	 .and().httpBasic()
    	 .and().csrf().disable()
    	 .authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                Object errorMessage = requestAttributes.getAttribute(RequestDispatcher.ERROR_MESSAGE, RequestAttributes.SCOPE_REQUEST);
                if (errorMessage != null) {
                	if(errorAttributes.get("status").toString().equals("401")){
                		errorAttributes.put("description", "Usuário ou credencial inválida");
                	}else if(errorAttributes.get("status").toString().equals("403")){
                		errorAttributes.put("description", "Usuário não autorizado");
                	}
                    errorAttributes.put("entity", null);
                    errorAttributes.remove("timestamp");
                    errorAttributes.remove("error");
                    errorAttributes.remove("exception");
                    
                }
                return errorAttributes;
            }
        };
    }
}
