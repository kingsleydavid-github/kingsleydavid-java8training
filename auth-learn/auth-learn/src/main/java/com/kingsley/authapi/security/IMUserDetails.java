package com.kingsley.authapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kingsley.authapi.model.users.IMUser;

@Service
public class IMUserDetails implements UserDetails {

	private IMUser imUser;
	
	public IMUserDetails() {}
	
	public IMUserDetails(IMUser imUser) {
		this.imUser= imUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(imUser.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return imUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return imUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}