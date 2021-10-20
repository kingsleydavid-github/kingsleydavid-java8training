package com.kingsley.authproviderdemo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kingsley.authproviderdemo.model.user.IMUser;
import com.kingsley.authproviderdemo.repo.IMUserRepo;

@Service
public class AuthUserDetailsService implements UserDetailsService{

	
	@Autowired
	IMUserRepo imUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//1. load from db
		//2. return hardcoded user
		// return new User("admin","Pass@123", new ArrayList<>());
		
		List<IMUser> imUser = imUserRepo.findByEmail(userName);
		if(imUser.isEmpty()) {
			throw new UsernameNotFoundException("Email not found");
		}
		return new IMUserDetails(imUser.get(0));
	}

}
