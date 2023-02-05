package com.gl.lab.restapi.studentmgmt.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gl.lab.restapi.studentmgmt.entity.Role;
import com.gl.lab.restapi.studentmgmt.entity.User;

@Service
public class StudentUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	public StudentUserDetails() {

	}

	public StudentUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
		List<Role> roles = null;
		if (user != null) {
			roles = user.getRoles();
		}
		if (roles != null) {
			for (Role role : roles) {
				grantedAuths.add(new SimpleGrantedAuthority(role.getName()));
			}
		}
		return grantedAuths;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
