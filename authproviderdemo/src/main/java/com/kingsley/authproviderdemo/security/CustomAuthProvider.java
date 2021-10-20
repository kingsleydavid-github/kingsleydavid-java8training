package com.kingsley.authproviderdemo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kingsley.authproviderdemo.model.user.IMUser;
import com.kingsley.authproviderdemo.model.user.IMUserAuthority;
import com.kingsley.authproviderdemo.repo.IMUserRepo;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

	@Autowired
	IMUserRepo imUserRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String pwdString = authentication.getCredentials().toString();
		List<IMUser> users = imUserRepo.findByEmail(username);
		if(users.size() > 0)
		{
			IMUser user = users.get(0);
			if(passwordEncoder.matches(pwdString, user.getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				Set<IMUserAuthority> userauthorities =  user.getAuthorities();
				userauthorities.forEach(i -> {
					SimpleGrantedAuthority ga = new SimpleGrantedAuthority(i.getAuthority());
					authorities.add(ga);
				});
				return new UsernamePasswordAuthenticationToken(username, pwdString, authorities); 
			} else {
				throw new BadCredentialsException("Username or password is incorrect");
			}
		} else {
			throw new BadCredentialsException("No registered user with this details");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class) ;
	}

}
