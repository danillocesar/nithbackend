package br.com.nith.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.nith.dao.UserDao;
import br.com.nith.models.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found:" + username);
        }
        boolean enabled = true;  
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities); 
        return userDetails;
    }
}
